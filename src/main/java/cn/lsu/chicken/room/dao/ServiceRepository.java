package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceRepository extends JpaRepository<Service,Integer>, JpaSpecificationExecutor<Service> {
}
