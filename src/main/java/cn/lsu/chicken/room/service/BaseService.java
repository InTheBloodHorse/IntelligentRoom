package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface BaseService<T, ID> {
    ID saveEntity(T entity);

    Integer updateEntity(T entity);

    Integer deleteEntity(ID id);

    T getEntityById(ID id);

    List<T> listEntity();

    PageDTO<T> listEntityByPage(PageHelper pageHelper);
}
