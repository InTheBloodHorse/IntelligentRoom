package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.AttendWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AttendWorkerRepository extends JpaRepository<AttendWorker, Integer>, JpaSpecificationExecutor<AttendWorker> {
    List<AttendWorker> findByApplyId(String applyId);


}
