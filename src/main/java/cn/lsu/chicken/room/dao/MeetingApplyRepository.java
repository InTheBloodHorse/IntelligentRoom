package cn.lsu.chicken.room.dao;


import cn.lsu.chicken.room.entity.MeetingApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingApplyRepository extends JpaRepository<MeetingApply, String>, JpaSpecificationExecutor<MeetingApply> {
    @Query(value = "select a.id,a.worker_id,user.name from meeting_apply as a LEFT join user ON a.worker_id = user.id;", nativeQuery = true)
    List<Object[]> test();
}

