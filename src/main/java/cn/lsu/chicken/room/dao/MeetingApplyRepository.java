package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.MeetingApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingApplyRepository extends JpaRepository<MeetingApply, String>, JpaSpecificationExecutor<MeetingApply> {
}

