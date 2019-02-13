package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.domain.MeetingRoomExample;

import java.util.List;

import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface MeetingRoomMapper {
    int countByExample(MeetingRoomExample example);

    int deleteByExample(MeetingRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MeetingRoom record);

    int insertSelective(MeetingRoom record);

    List<MeetingRoom> selectByExampleWithBLOBs(MeetingRoomExample example);

    List<MeetingRoomDTO> selectByExample(MeetingRoomExample example);

    MeetingRoomDTO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MeetingRoom record, @Param("example") MeetingRoomExample example);

    int updateByExampleWithBLOBs(@Param("record") MeetingRoom record, @Param("example") MeetingRoomExample example);

    int updateByExample(@Param("record") MeetingRoom record, @Param("example") MeetingRoomExample example);

    int updateByPrimaryKeySelective(MeetingRoom record);

    int updateByPrimaryKeyWithBLOBs(MeetingRoom record);

    int updateByPrimaryKey(MeetingRoom record);
}