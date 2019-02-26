package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.form.meetingroom.MeetingRoomQueryForm;

import java.util.List;

public interface MeetingRoomService extends BaseService<MeetingRoomDTO, MeetingRoom, Integer, MeetingRoomQueryForm> {

    Integer updateTag(Integer meetingRoomId, List<Integer> tagList);

    Integer uploadCover(MeetingRoom meetingRoom);
}
