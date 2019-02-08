package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Building;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.entity.mapper.BuildingMapper;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public void saveEntity(Building building) {
        String location = building.getLocation();
        judgeExistsByLocation(location);
        buildingMapper.addEntity(building);
    }

    @Override
    public void updateEntity(Building building) {
        String id = building.getId();
        String location = building.getLocation();
        Boolean haveBuilding = buildingMapper.judgeExistsByIdAndLocation(id, location);
        if (!haveBuilding) {
            judgeExistsByLocation(location);
        }
        buildingMapper.updateEntity(building);
    }

    @Override
    public void deleteEntity(String s) {
        buildingMapper.deleteEntityById(s);
    }

    @Override
    public Building getEntity(String s) {
        return buildingMapper.getEntity(s);
    }

    @Override
    public List<Building> listEntity() {
        return buildingMapper.listEntity();
    }

    @Override
    public PageDTO<Building> listEntityByPage(PageHelper pageHelper) {
        Long total = buildingMapper.count();
        List<Building> data = new ArrayList<>();
        if (!total.equals(0)) {
            data = buildingMapper.listEntityByPage(pageHelper);
        }
        PageDTO<Building> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    private void judgeExistsByLocation(String location) {
        Boolean result = buildingMapper.judgeExistsByLocation(location);
        if (result == true) {
            throw new GlobalException(ResultEnum.BUILDING_IS_EXITS);
        }
    }
}
