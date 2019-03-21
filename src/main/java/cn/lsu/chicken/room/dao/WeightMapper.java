package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.Weight;
import cn.lsu.chicken.room.domain.WeightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface WeightMapper {
    int countByExample(WeightExample example);

    int deleteByExample(WeightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Weight record);

    int insertSelective(Weight record);

    List<Weight> selectByExample(WeightExample example);

    Weight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Weight record, @Param("example") WeightExample example);

    int updateByExample(@Param("record") Weight record, @Param("example") WeightExample example);

    int updateByPrimaryKeySelective(Weight record);

    int updateByPrimaryKey(Weight record);
}