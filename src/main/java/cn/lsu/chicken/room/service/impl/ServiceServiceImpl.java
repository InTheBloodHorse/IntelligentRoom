package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.ServiceMapper;
import cn.lsu.chicken.room.domain.ServiceExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.service.ServiceQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.ServiceService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public Integer saveEntity(cn.lsu.chicken.room.domain.Service entity) {
        serviceMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(cn.lsu.chicken.room.domain.Service entity) {
        return serviceMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return serviceMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public cn.lsu.chicken.room.domain.Service getEntityById(Integer integer) {
        return serviceMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<cn.lsu.chicken.room.domain.Service> listEntityByQueryForm(ServiceQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        ServiceExample example = (ServiceExample) QueryFormUtil.getExample(ServiceExample.class, page, size, order);
        ServiceExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, ServiceQueryForm.QUERTFORMLIST);
        List<cn.lsu.chicken.room.domain.Service> data = serviceMapper.selectByExample(example);
        Integer total = serviceMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<cn.lsu.chicken.room.domain.Service> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }


    @Override
    public Integer serviceDone(Integer serviceId) {
        cn.lsu.chicken.room.domain.Service service = new cn.lsu.chicken.room.domain.Service();
        service.setId(serviceId);
        service.setServiceTime(new Date());
        return serviceMapper.updateByPrimaryKeySelective(service);
    }

}
