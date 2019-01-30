package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.BuildingRepository;
import cn.lsu.chicken.room.entity.Building;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImp implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public void saveBuilding(Building building) {
        if (buildingRepository.existsByLocation(building.getLocation()) == true) {
            throw new GlobalException(ResultEnum.BUILDING_IS_EXITS);
        }
        buildingRepository.save(building);
    }

    @Override
    public void deleteBuilding(String id) {
        try {
            buildingRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.BUILDING_NOT_EXITS);
        }
    }

    @Override
    public List<Building> getAllBuild() {
        return buildingRepository.findAll();
    }

    @Override
    public Page<Building> getAllBuild(Pageable pageable) {
        return buildingRepository.findAll(pageable);
    }
}
