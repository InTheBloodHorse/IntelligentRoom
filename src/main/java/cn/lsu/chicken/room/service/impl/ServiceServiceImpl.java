package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.ServiceMapper;
import cn.lsu.chicken.room.domain.ServiceExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.ServiceService;
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
        return serviceMapper.updateByPrimaryKey(entity);
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
    public List<cn.lsu.chicken.room.domain.Service> listEntity() {
        return serviceMapper.selectByExample(new ServiceExample());
    }

    @Override
    public PageDTO<cn.lsu.chicken.room.domain.Service> listEntityByPage(PageHelper pageHelper) {
        ServiceExample serviceExample = new ServiceExample(pageHelper.getPage(), pageHelper.getSize());
        List<cn.lsu.chicken.room.domain.Service> data = serviceMapper.selectByExample(serviceExample);
        Integer total = serviceMapper.countByExample(new ServiceExample());
        PageDTO<cn.lsu.chicken.room.domain.Service> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    @Override
    public Integer serviceDone(Integer serviceId) {
        cn.lsu.chicken.room.domain.Service service = new cn.lsu.chicken.room.domain.Service();
        service.setId(serviceId);
        service.setServiceTime(new Date());
        return serviceMapper.updateByPrimaryKeySelective(service);
    }

    @Override
    public List<cn.lsu.chicken.room.domain.Service> listServiceByUserId(Integer userId) {
        ServiceExample serviceExample = new ServiceExample();
        ServiceExample.Criteria criteria = serviceExample.createCriteria();
        criteria.andWorkerIdEqualTo(userId);
        return serviceMapper.selectByExample(serviceExample);
    }

    @Override
    public PageDTO<cn.lsu.chicken.room.domain.Service> listServiceByUserIdByPage(PageHelper pageHelper, Integer userId) {
        ServiceExample serviceExample = new ServiceExample(pageHelper.getPage(), pageHelper.getSize());
        ServiceExample.Criteria criteria = serviceExample.createCriteria();
        criteria.andWorkerIdEqualTo(userId);
        List<cn.lsu.chicken.room.domain.Service> data = serviceMapper.selectByExample(serviceExample);

        serviceExample.setPage(null);
        serviceExample.setSize(null);
        Integer total = serviceMapper.countByExample(serviceExample);
        PageDTO<cn.lsu.chicken.room.domain.Service> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }
}
