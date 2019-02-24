package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.meetingapply.MeetingApplyQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface MeetingApplyService extends BaseService<MeetingApply, MeetingApply, Integer, MeetingApplyQueryForm> {

    Integer addAttenderWorker(Integer meetingApplyId, List<Integer> userId);

    Integer deleteAttenderWorker(Integer meetingApplyId, List<Integer> userId);

    // 参与会议
    List<MeetingApply> listMeetingApplyByUserId(Integer id);

}
