package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.EquipmentMapper;
import cn.lsu.chicken.room.domain.Equipment;
import cn.lsu.chicken.room.domain.EquipmentExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.equipment.EquipmentQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.EquipmentService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Integer saveEntity(Equipment entity) {
        judgeExistByName(entity.getName());
        equipmentMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(Equipment entity) {
        String name = entity.getName();
        Integer id = entity.getId();
        Boolean result = judgeExistByIdAndName(id,name);
        if(result==false){
            judgeExistByName(name);
        }
        return equipmentMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return equipmentMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public Equipment getEntityById(Integer integer) {
        return equipmentMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<Equipment> listEntityByQueryForm(EquipmentQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        EquipmentExample example = (EquipmentExample) QueryFormUtil.getExample(EquipmentExample.class, page, size, order);
        EquipmentExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, EquipmentQueryForm.QUERTFORMLIST);
        List<Equipment> data = equipmentMapper.selectByExample(example);
        Integer total = equipmentMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<Equipment> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }

    private void judgeExistByName(String name) {
        EquipmentExample equipmentExample = getEquipmentExample(name);
        Integer result = equipmentMapper.countByExample(equipmentExample);
        if (result > 0) {
            throw new GlobalException(ResultEnum.EQUIPMENT_IS_EXIST);
        }

    }

    private Boolean judgeExistByIdAndName(Integer id, String name) {
        EquipmentExample equipmentExample = getEquipmentExample(name);
        equipmentExample.getOredCriteria().get(0).andIdEqualTo(id);
        Integer result = equipmentMapper.countByExample(equipmentExample);
        return result > 0 ? true : false;

    }

    private EquipmentExample getEquipmentExample(String name) {
        EquipmentExample equipmentExample = new EquipmentExample();
        EquipmentExample.Criteria criteria = equipmentExample.createCriteria();
        criteria.andNameEqualTo(name);
        return equipmentExample;
    }
}
