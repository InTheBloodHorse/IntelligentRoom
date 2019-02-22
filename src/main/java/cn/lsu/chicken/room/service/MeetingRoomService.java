package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;

import java.util.List;

public interface MeetingRoomService extends BaseService<MeetingRoomDTO, MeetingRoom, Integer,Object> {

    Integer updateTag(Integer meetingRoomId, List<Integer> tagList);
}
