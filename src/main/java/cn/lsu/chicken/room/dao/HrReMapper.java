package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.HrRe;
import cn.lsu.chicken.room.domain.HrReExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrReMapper {
    int countByExample(HrReExample example);

    int deleteByExample(HrReExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HrRe record);

    int insertSelective(HrRe record);

    List<HrRe> selectByExample(HrReExample example);

    HrRe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HrRe record, @Param("example") HrReExample example);

    int updateByExample(@Param("record") HrRe record, @Param("example") HrReExample example);

    int updateByPrimaryKeySelective(HrRe record);

    int updateByPrimaryKey(HrRe record);
}