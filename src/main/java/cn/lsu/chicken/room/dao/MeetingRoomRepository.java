package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer>, JpaSpecificationExecutor<MeetingRoom> {

}
