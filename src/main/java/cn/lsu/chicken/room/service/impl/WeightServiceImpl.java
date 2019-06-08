package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.MeetingApplyMapper;
import cn.lsu.chicken.room.dao.WeightMapper;
import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.domain.Weight;
import cn.lsu.chicken.room.domain.WeightExample;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ApplyEnum;
import cn.lsu.chicken.room.form.meetingapply.MeetingApplyQueryForm;
import cn.lsu.chicken.room.form.meetingroom.MeetingRoomQueryForm;
import cn.lsu.chicken.room.form.weight.WeightQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.MeetingApplyService;
import cn.lsu.chicken.room.service.MeetingRoomService;
import cn.lsu.chicken.room.service.WeightService;
import cn.lsu.chicken.room.utils.DateUtil;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeightServiceImpl implements WeightService {

    @Autowired
    private MeetingRoomService meetingRoomService;
    @Autowired
    private MeetingApplyService meetingApplyService;

    @Autowired
    private WeightMapper weightMapper;

    @Autowired
    private MeetingApplyMapper meetingApplyMapper;

    @Override
    public Integer saveEntity(Weight entity) {
        weightMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(Weight entity) {
        return weightMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return weightMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public Weight getEntityById(Integer integer) {
        return weightMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<Weight> listEntityByQueryForm(WeightQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        WeightExample example = (WeightExample) QueryFormUtil.getExample(WeightExample.class, page, size, order);
        WeightExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, WeightQueryForm.QUERTFORMLIST);
        List<Weight> data = weightMapper.selectByExample(example);
        Integer total = weightMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<Weight> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }

    @Override
    public Map<Integer, Integer> calculateWeight(List<Weight> weightList) {
        Map<Integer, Integer> weight = new HashMap<>();
        for (Weight w : weightList) {
            double total = WeightService.DEFAULT_TOTAL;
            total = total - w.getUpdateTime() * 0.5 - w.getDelayTime() * 1 - w.getCancelTime() * 0.5;
            weight.put(w.getCompanyId(), Integer.valueOf(new Double(total).intValue()));
        }
        return weight;
    }

    // 加权随机算法实现
    @Override
    public Integer weightRandom(Map<Integer, Integer> weight) {
        Integer sum = 0;
        Set<Integer> keys = weight.keySet();
        for (Integer i : keys) {
            sum += weight.get(i);
        }
        Integer random = new Random().nextInt(sum);
        Integer current_sum = 0;
        for (Integer i : keys) {
            current_sum += weight.get(i);
            if (random <= current_sum) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Integer applyInTwoDay(MeetingApply entity, Date currentDay) {
        MeetingRoomQueryForm meetingRoomQueryForm = new MeetingRoomQueryForm();
        meetingRoomQueryForm.setVolume(entity.getAttendance() + ",");
        Integer roomId = entity.getRoomId();
        if (roomId != null) {
            meetingRoomQueryForm.setId(roomId);
        }
        List<MeetingRoomDTO> data = meetingRoomService.listEntityByQueryForm(meetingRoomQueryForm).getData();
        data.sort(Comparator.comparing(MeetingRoomDTO::getVolume));
        for (MeetingRoomDTO room : data) {
            MeetingApplyQueryForm meetingApplyQueryForm = new MeetingApplyQueryForm();
            meetingApplyQueryForm.setRoomId(room.getId());
            meetingApplyQueryForm.setBeginTime(DateUtil.simpleDateFormat.format(DateUtil.getDay(currentDay))
                    + "," + DateUtil.simpleDateFormat.format(DateUtil.tomorrowDay(currentDay)));
            List<MeetingApply> applyList = meetingApplyService.listEntityByQueryForm(meetingApplyQueryForm).getData();
            // 得到会议室预约的预约单
            Map<Long, Long> segment = new HashMap<>();
            for (MeetingApply apply : applyList) {
                // 假如会议已结束
                if (apply.getStatus() == 3) continue;
                Date beginTime = apply.getBeginTime();
                Calendar calendarBegin = Calendar.getInstance();
                calendarBegin.setTime(beginTime);
                calendarBegin.add(Calendar.MINUTE, -30);
                Date endTime = apply.getEndTime();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endTime);
                calendarEnd.add(Calendar.MINUTE, 30);
                segment.put(calendarBegin.getTimeInMillis(), calendarEnd.getTimeInMillis());
            }
            Boolean result = DateUtil.judgeIsCut(segment,
                    entity.getBeginTime().getTime(), entity.getEndTime().getTime());
            // 不相交 预约成功
            if (result == false) {
                entity.setStatus(1);
                entity.setRoomId(room.getId());
                meetingApplyMapper.insertSelective(entity);
                meetingApplyMapper.addAttenderWorker(entity.getId(), Arrays.asList(entity.getWorkerId()));
                return entity.getId();
            }
        }
        return ApplyEnum.BE_APPLY.getCode();
    }

    @Override
    public void applyInWeek() {
        /*
            查出所有需要当前进入调度的预约。
            1：检查冲突，推到一个队列 （不可调剂 可以考虑加权）

         */
        MeetingApplyQueryForm meetingApplyQueryForm = new MeetingApplyQueryForm();
        meetingApplyQueryForm.setStatus(0);
        List<MeetingApply> data = meetingApplyService.listEntityByQueryForm(meetingApplyQueryForm).getData();
        // 筛选出3 - 7天内
        List<MeetingApply> weekData = data.stream().filter(
                (MeetingApply meetingApply) ->
                        judgeInWeek(meetingApply.getApplyTime(), meetingApply.getBeginTime())
        ).collect(Collectors.toList());


        List<MeetingRoomDTO> meetingRoomDTOList = meetingRoomService.listEntityByQueryForm(new MeetingRoomQueryForm()).getData();
        Map<Integer, List<MeetingApply>> filterApplyMap = new HashMap() {
            {
                // 0 代表对会议室无限制
                put(0, new ArrayList<MeetingApply>());
            }
        };

        // 将预约相同的房间号放在一起
        Map<MeetingApply, List<MeetingApply>> conflict = new HashMap<>();
        for (MeetingApply apply : weekData) {
            if (apply.getRoomId() == null) {
                filterApplyMap.get(0).add(apply);
            } else {
                Integer roomId = apply.getRoomId();
                if (filterApplyMap.containsKey(roomId) == false) {
                    filterApplyMap.put(roomId, new ArrayList<>());
                }
                filterApplyMap.get(roomId).add(apply);
            }

            // 得到冲突的集合
            for (MeetingApply elseApply : weekData) {
                if (apply.getId() == elseApply.getId()) continue;
                if (judgeIsConflict(apply, elseApply) == true) {
                    if (conflict.containsKey(apply) == false) {
                        conflict.put(apply, new ArrayList<>());
                    }
                    conflict.get(apply).add(elseApply);
                }
            }
        }
        List<MeetingApply> needApply = weekData;
        List<MeetingApply> applyed = new ArrayList<>();
        while (needApply.size() != 0) {
            for (int i = 0; i < needApply.size(); i++) {
                MeetingApply apply = needApply.get(i);
                if (apply.getRoomId() == null) {
                    // 可以预约任意的
                    for (MeetingRoomDTO room : meetingRoomDTOList) {
                        return;
                    }
                } else {
                    // 只能预约选定的会议室
                    List<MeetingApply> applyConflict = conflict.get(apply);
                    if (applyConflict.size() == 0) {
                        // 无冲突 直接预订
                        applyed.add(apply);
                        needApply.remove(needApply);
                        i--;
                    } else {
                        return;
                    }
                }
            }

        }

        System.out.println(weekData);
    }

    private Boolean judgeInWeek(Date applyTime, Date beginTime) {
        Long distance_meeting = beginTime.getTime() - applyTime.getTime();
        Long distance_result = new Date().getTime() - applyTime.getTime();
        Long day = Long.valueOf(24 * 60 * 60 * 1000);
        if (distance_meeting <= 7 * day && distance_result <= 1 * day) {
            return true;
        }
        return false;
    }

    private Boolean judgeIsConflict(MeetingApply apply1, MeetingApply apply2) {
        // 左右空出15分钟
        Long clock = Long.valueOf(15 * 60 * 1000);
        Long beginApply1 = apply1.getBeginTime().getTime() - clock;
        Long endApply1 = apply1.getBeginTime().getTime() + clock;
        Long beginApply2 = apply2.getBeginTime().getTime() - clock;
        Long endApply2 = apply2.getBeginTime().getTime() + clock;

        if (beginApply1 >= endApply2 || endApply1 <= beginApply2) {
            return false;
        }
        return true;
    }
}
