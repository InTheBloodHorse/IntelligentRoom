package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.Service;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.service.ServiceQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface ServiceService extends BaseService<Service, Service, Integer, ServiceQueryForm> {

    Integer serviceDone(Integer serviceId);


}
