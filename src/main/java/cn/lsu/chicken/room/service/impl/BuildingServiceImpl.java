package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.BuildingMapper;
import cn.lsu.chicken.room.domain.Building;
import cn.lsu.chicken.room.domain.BuildingExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public Integer saveEntity(Building entity) {
        String location = entity.getLocation();
        judgeExistByLocation(location);
        buildingMapper.insert(entity);
        return entity.getId();

    }

    @Override
    public Integer updateEntity(Building entity) {
        Integer id = entity.getId();
        String location = entity.getLocation();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        //假如当前标签修改了名称
        if (judgeExistByIdAndLocation(id, location) == false) {
            //校验名称是否合法
            judgeExistByLocation(location);
        }
        return buildingMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return buildingMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public Building getEntityById(Integer integer) {
        return buildingMapper.selectByPrimaryKey(integer);
    }

    @Override
    public List<Building> listEntity() {
        return buildingMapper.selectByExample(new BuildingExample());
    }

    @Override
    public PageDTO<Building> listEntityByPage(PageHelper pageHelper) {
        BuildingExample buildingExample = new BuildingExample(pageHelper.getPage(), pageHelper.getSize());
        List<Building> data = buildingMapper.selectByExample(buildingExample);
        Integer total = buildingMapper.countByExample(new BuildingExample());
        PageDTO<Building> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    private void judgeExistByLocation(String name) {
        BuildingExample buildingExample = new BuildingExample();
        BuildingExample.Criteria criteria = buildingExample.createCriteria();
        criteria.andLocationEqualTo(name);
        List<Building> result = buildingMapper.selectByExample(buildingExample);
        if (result.size() != 0) {
            throw new GlobalException(ResultEnum.BUILDING_IS_EXIST);
        }
    }

    private Boolean judgeExistByIdAndLocation(Integer id, String location) {
        BuildingExample buildingExample = new BuildingExample();
        BuildingExample.Criteria criteria = buildingExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andLocationEqualTo(location);
        List<Building> result = buildingMapper.selectByExample(buildingExample);
        if (result.size() == 0) {
            return false;
        }
        return true;
    }
}
