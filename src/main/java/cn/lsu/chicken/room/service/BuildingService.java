package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    //保存大楼
    void saveBuilding(Building building);

    void deleteBuilding(String id);

    List<Building> getAllBuild();

    PageDTO<Building> getAllBuild(Pageable pageable);

}
