package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.MeetingApplyMapper;
import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.domain.MeetingApplyExample;
import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ApplyEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.BaseQueryForm;
import cn.lsu.chicken.room.form.meetingapply.AttenderQueryForm;
import cn.lsu.chicken.room.form.meetingapply.MeetingApplyQueryForm;
import cn.lsu.chicken.room.form.meetingroom.MeetingRoomQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.MeetingApplyService;
import cn.lsu.chicken.room.service.MeetingRoomService;
import cn.lsu.chicken.room.service.WeightService;
import cn.lsu.chicken.room.utils.DateUtil;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MeetingApplyServiceImpl implements MeetingApplyService {

    @Autowired
    private WeightService weightService;


    @Autowired
    private MeetingApplyMapper meetingApplyMapper;

    @Override
    public Integer saveEntity(MeetingApply entity) {
        judgeTime(entity);
        Date currentDay = new Date();
        entity.setApplyTime(currentDay);
        Date applyBeginDay = entity.getBeginTime();
        if (DateUtil.judgeInTwoDay(currentDay, applyBeginDay)) {
            return weightService.applyInTwoDay(entity, currentDay);
        }
        meetingApplyMapper.insertSelective(entity);
        meetingApplyMapper.addAttenderWorker(entity.getId(), Arrays.asList(entity.getWorkerId()));
        return ApplyEnum.BE_APPLY.getCode();
    }

    @Override
    public Integer updateEntity(MeetingApply entity) {
        judgeTime(entity);
        entity.setApplyUpdateTime(new Date());
        return meetingApplyMapper.updateByPrimaryKeySelective(entity);
    }

    private void judgeTime(MeetingApply entity) {
        Date beginTime = entity.getBeginTime();
        Date endTime = entity.getEndTime();
        if (beginTime.after(endTime)) {
            throw new GlobalException(ResultEnum.DATE_IS_INVALID);
        }
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return meetingApplyMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public MeetingApply getEntityById(Integer integer) {
        return meetingApplyMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<MeetingApply> listEntityByQueryForm(MeetingApplyQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        MeetingApplyExample example = (MeetingApplyExample) QueryFormUtil.getExample(MeetingApplyExample.class, page, size, order);
        MeetingApplyExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, MeetingApplyQueryForm.QUERTFORMLIST);
        List<MeetingApply> data = meetingApplyMapper.selectByExample(example);
        Integer total = meetingApplyMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<MeetingApply> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }

    @Override
    public Integer addAttenderWorker(Integer meetingApplyId, List<Integer> userId) {
        Integer worker_id = meetingApplyMapper.selectByPrimaryKey(meetingApplyId).getWorkerId();
        userId.add(worker_id);
        Set<Integer> tempSet = new HashSet<>(userId);

        return meetingApplyMapper.addAttenderWorker(meetingApplyId, new ArrayList<>(tempSet));
    }

    @Override
    public Integer deleteAttenderWorker(Integer meetingApplyId, List<Integer> userId) {
        return meetingApplyMapper.deleteAttenderWorker(meetingApplyId, userId);
    }

    @Override
    public PageDTO<MeetingApply> listMeetingApplyByUserId(AttenderQueryForm attenderQueryForm) {
        Integer page = attenderQueryForm.getPage();
        Integer size = attenderQueryForm.getSize();
        String order = attenderQueryForm.getOrder();
        MeetingApplyExample example = (MeetingApplyExample) QueryFormUtil.getExample(MeetingApplyExample.class, page, size, order);
        MeetingApplyExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, attenderQueryForm, AttenderQueryForm.QUERTFORMLIST);
        Integer id = attenderQueryForm.getUserId();
        List<MeetingApply> data = meetingApplyMapper.selectByAttendUserIdExample(id, example);
        Integer total = meetingApplyMapper.countByAttendUserIdExample(id);
        PageHelper pageHelper = example;
        PageDTO<MeetingApply> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }


}
