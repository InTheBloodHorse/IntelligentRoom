package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.entity.MeetingRoom;
import cn.lsu.chicken.room.form.MeetingRoomQueryForm;

import java.util.List;

public interface MeetingRoomService {

    void saveMeetingRoom(MeetingRoom meetingRoom);

    void deleteMeetingRoom(Integer id);

    MeetingRoomDTO findMeetRoomById(Integer id);

    List<MeetingRoomDTO> findByManyConditions(MeetingRoomQueryForm meetingRoomQueryForm);
}
