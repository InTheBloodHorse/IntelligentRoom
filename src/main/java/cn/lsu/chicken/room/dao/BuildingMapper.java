package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.Building;
import cn.lsu.chicken.room.domain.BuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface BuildingMapper {
    int countByExample(BuildingExample example);

    int deleteByExample(BuildingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    int insertSelective(Building record);

    List<Building> selectByExample(BuildingExample example);

    Building selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Building record, @Param("example") BuildingExample example);

    int updateByExample(@Param("record") Building record, @Param("example") BuildingExample example);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);

}