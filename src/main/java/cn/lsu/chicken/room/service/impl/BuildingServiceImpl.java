package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.BuildingMapper;
import cn.lsu.chicken.room.domain.Building;
import cn.lsu.chicken.room.domain.BuildingExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.building.BuildingQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.BuildingService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
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
        buildingMapper.insertSelective(entity);
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
        return buildingMapper.updateByPrimaryKeySelective(entity);
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
    public PageDTO<Building> listEntityByQueryForm(BuildingQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        BuildingExample buildingExample = (BuildingExample) QueryFormUtil.getExample(BuildingExample.class, page, size, order);
        BuildingExample.Criteria criteria = buildingExample.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, BuildingQueryForm.QUERTFORMLIST);
        List<Building> data = buildingMapper.selectByExample(buildingExample);
        Integer total = buildingMapper.countByExample(buildingExample);
        PageHelper pageHelper = buildingExample;
        PageDTO<Building> tagPageDTO = new PageDTO<>(pageHelper, total, data);
        return tagPageDTO;
    }


    private void judgeExistByLocation(String name) {
        BuildingExample buildingExample = new BuildingExample();
        BuildingExample.Criteria criteria = buildingExample.createCriteria();
        criteria.andLocationEqualTo(name);
        Integer result = buildingMapper.countByExample(buildingExample);
        if (result != 0) {
            throw new GlobalException(ResultEnum.BUILDING_IS_EXIST);
        }
    }

    private Boolean judgeExistByIdAndLocation(Integer id, String location) {
        BuildingExample buildingExample = new BuildingExample();
        BuildingExample.Criteria criteria = buildingExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andLocationEqualTo(location);
        Integer result = buildingMapper.countByExample(buildingExample);
        return result > 0 ? true : false;
    }

}
