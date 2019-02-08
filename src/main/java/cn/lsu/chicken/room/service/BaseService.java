package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface BaseService<T, ID> {
    void saveEntity(T entity);

    void updateEntity(T entity);

    void deleteEntity(ID id);

    T getEntity(ID id);

    List<T> listEntity();

    PageDTO<T> listEntityByPage(PageHelper pageHelper);
}
