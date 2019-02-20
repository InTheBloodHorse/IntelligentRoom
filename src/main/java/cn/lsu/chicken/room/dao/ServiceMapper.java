package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.Service;
import cn.lsu.chicken.room.domain.ServiceExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface ServiceMapper {
    int countByExample(ServiceExample example);

    int deleteByExample(ServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    List<Service> selectByExample(ServiceExample example);

    Service selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByExample(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByPrimaryKeySelective(Service record);


    int updateByPrimaryKey(Service record);
}