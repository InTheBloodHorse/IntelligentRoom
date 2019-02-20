package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.Service;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface ServiceService extends BaseService<Service, Service, Integer> {

    Integer serviceDone(Integer serviceId);

    List<Service> listServiceByUserId(Integer userId);

    PageDTO<Service> listServiceByUserIdByPage(PageHelper pageHelper, Integer userId);

}
