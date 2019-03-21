package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.CameraMapper;
import cn.lsu.chicken.room.domain.Camera;
import cn.lsu.chicken.room.domain.CameraExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.camera.CameraQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.CameraService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CameraMapper cameraMapper;

    @Override
    public Integer saveEntity(Camera entity) {
        entity.setCreateTime(new Date());
        cameraMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(Camera entity) {
        return cameraMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return cameraMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public Camera getEntityById(Integer integer) {
        return cameraMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<Camera> listEntityByQueryForm(CameraQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        CameraExample example = (CameraExample) QueryFormUtil.getExample(CameraExample.class, page, size, order);
        CameraExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, CameraQueryForm.QUERTFORMLIST);
        List<Camera> data = cameraMapper.selectByExample(example);
        Integer total = cameraMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<Camera> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }

}


