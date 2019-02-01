package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.BuildingRepository;
import cn.lsu.chicken.room.dto.PageDTO;
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
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public void saveBuilding(Building building) {
        if (building.getId() == null) {
            if (buildingRepository.existsByLocation(building.getLocation()) == true) {
                throw new GlobalException(ResultEnum.BUILDING_IS_EXITS);
            }
        } else {
            Building result = buildingRepository.findById(building.getId()).orElse(null);
            if (result == null) {
                throw new GlobalException(ResultEnum.BUILDING_NOT_EXITS);
            }
            if (!building.getLocation().equals(result.getLocation())) {
                if (buildingRepository.existsByLocation(building.getLocation()) == true) {
                    throw new GlobalException(ResultEnum.BUILDING_IS_EXITS);
                }
            }
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
    public PageDTO<Building> getAllBuild(Pageable pageable) {
        Page<Building> page = buildingRepository.findAll(pageable);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(pageable.getPageNumber());
        pageDTO.setSize(pageable.getPageSize());
        pageDTO.setTotal(page.getTotalElements());
        pageDTO.setTotalPage(page.getTotalPages());
        pageDTO.setData(page.getContent());
        return pageDTO;
    }
}
