package cn.lsu.chicken.room.entity.mapper;

import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface BaseMapper<T,ID> {

    void addEntity(T entity);

    void updateEntity(T entity);

    void deleteEntityById(ID id);

    T getEntity(ID id);

    List<T> listEntity();

    List<T> listEntityByPage(PageHelper pageHelper);

    Long count();
}
