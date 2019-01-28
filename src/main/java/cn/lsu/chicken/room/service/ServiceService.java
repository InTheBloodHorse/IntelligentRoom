package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.Service;

import java.util.List;

public interface ServiceService {
    Service addService(Service service);

    List<Service> getAllSeverLog();
}
