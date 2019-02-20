package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface MeetingApplyService extends BaseService<MeetingApply, MeetingApply, Integer> {

    Integer addAttenderWorker(Integer meetingApplyId, List<Integer> userId);

    Integer deleteAttenderWorker(Integer meetingApplyId, List<Integer> userId);

    // 参与会议
    List<MeetingApply> listMeetingApplyByUserId(Integer id);

    // 多条件
    List<MeetingApply> listMeetingApplyByConditions(MeetingApply meetingApply);

    PageDTO<MeetingApply> listMeetingApplyByConditionsByPage(PageHelper pageHelper, MeetingApply meetingApply);
}
