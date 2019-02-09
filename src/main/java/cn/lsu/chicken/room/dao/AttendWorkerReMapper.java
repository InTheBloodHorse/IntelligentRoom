package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.AttendWorkerRe;
import cn.lsu.chicken.room.domain.AttendWorkerReExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttendWorkerReMapper {
    int countByExample(AttendWorkerReExample example);

    int deleteByExample(AttendWorkerReExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttendWorkerRe record);

    int insertSelective(AttendWorkerRe record);

    List<AttendWorkerRe> selectByExample(AttendWorkerReExample example);

    AttendWorkerRe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttendWorkerRe record, @Param("example") AttendWorkerReExample example);

    int updateByExample(@Param("record") AttendWorkerRe record, @Param("example") AttendWorkerReExample example);

    int updateByPrimaryKeySelective(AttendWorkerRe record);

    int updateByPrimaryKey(AttendWorkerRe record);
}