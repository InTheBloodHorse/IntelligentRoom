package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BuildingRepository extends JpaRepository<Building, String>, JpaSpecificationExecutor<Building> {
    boolean existsByLocation(String name);
}
