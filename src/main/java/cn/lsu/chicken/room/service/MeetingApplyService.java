package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.MeetingApply;

import java.util.List;

public interface MeetingApplyService {
    //预约
    MeetingApply addApply(MeetingApply meetingApply);

    //修改信息
    MeetingApply updateApply(MeetingApply meetingApply);

    //根据编号删除
    void deleteApply(String id);

    List<MeetingApply> findByUserId(Integer id);
}
