package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.MeetingApplyMapper;
import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.domain.MeetingApplyExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.MeetingApplyService;
import cn.lsu.chicken.room.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeetingApplyServiceImpl implements MeetingApplyService {

    @Autowired
    private MeetingApplyMapper meetingApplyMapper;

    @Override
    public Integer saveEntity(MeetingApply entity) {
        return meetingApplyMapper.insertSelective(entity);
    }

    @Override
    public Integer updateEntity(MeetingApply entity) {
        entity.setApplyUpdateTime(new Date());
        return meetingApplyMapper.updateByPrimaryKey(entity);
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
    public List<MeetingApply> listEntity() {
        return meetingApplyMapper.selectByExample(new MeetingApplyExample());
    }

    @Override
    public PageDTO<MeetingApply> listEntityByPage(PageHelper pageHelper) {
        MeetingApplyExample meetingApplyExample = new MeetingApplyExample(pageHelper.getPage(), pageHelper.getSize());
        List<MeetingApply> data = meetingApplyMapper.selectByExample(meetingApplyExample);
        Integer total = meetingApplyMapper.countByExample(new MeetingApplyExample());
        PageDTO<MeetingApply> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    @Override
    public Integer addAttenderWorker(Integer meetingApplyId, List<Integer> userId) {
        return meetingApplyMapper.addAttenderWorker(meetingApplyId, userId);
    }

    @Override
    public Integer deleteAttenderWorker(Integer meetingApplyId, List<Integer> userId) {
        return meetingApplyMapper.deleteAttenderWorker(meetingApplyId, userId);
    }

    @Override
    public List<MeetingApply> listMeetingApplyByUserId(Integer id) {
        return meetingApplyMapper.selectByAttendUserId(id);
    }

    @Override
    public List<MeetingApply> listMeetingApplyByConditions(MeetingApply meetingApply) {
        MeetingApplyExample meetingApplyExample = new MeetingApplyExample();
        return meetingApplyMapper.selectByExample(getMeetingApplyExampleByConditions(meetingApplyExample, meetingApply));
    }

    @Override
    public PageDTO<MeetingApply> listMeetingApplyByConditionsByPage(PageHelper pageHelper, MeetingApply meetingApply) {
        MeetingApplyExample meetingApplyExample = new MeetingApplyExample(pageHelper.getPage(), pageHelper.getSize());
        meetingApplyExample = getMeetingApplyExampleByConditions(meetingApplyExample, meetingApply);
        meetingApplyExample.setPage(null);
        meetingApplyExample.setSize(null);
        Integer total = meetingApplyMapper.countByExample(meetingApplyExample);
        List<MeetingApply> data = meetingApplyMapper.selectByExample(meetingApplyExample);
        PageDTO<MeetingApply> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    private MeetingApplyExample getMeetingApplyExampleByConditions(MeetingApplyExample meetingApplyExample, MeetingApply meetingApply) {
        MeetingApplyExample.Criteria criteria = meetingApplyExample.createCriteria();
        Integer workerId = meetingApply.getWorkerId();
        if (workerId != null) {
            criteria.andWorkerIdEqualTo(workerId);
        }
        Date beginTime = meetingApply.getBeginTime();
        Date endTime = meetingApply.getEndTime();
        if (beginTime != null || endTime != null) {
            beginTime = DateUtil.max(beginTime);
            endTime = DateUtil.min(endTime);
            System.out.println(beginTime);
            System.out.println(endTime);
            criteria.andBeginTimeBetween(beginTime, endTime);
        }
        Integer status = meetingApply.getStatus();
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        return meetingApplyExample;
    }
}
