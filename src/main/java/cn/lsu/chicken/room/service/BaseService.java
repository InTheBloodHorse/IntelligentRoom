package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.helper.PageHelper;

import java.util.List;

public interface BaseService<R, T, ID, F> {
    ID saveEntity(T entity);

    Integer updateEntity(T entity);

    Integer deleteEntity(ID id);

    R getEntityById(ID id);

    PageDTO<R> listEntityByQueryForm(F entityQueryForm);


}
