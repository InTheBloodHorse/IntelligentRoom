package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.Department;
import cn.lsu.chicken.room.domain.DepartmentExample;
import java.util.List;

import cn.lsu.chicken.room.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentMapper {
    int countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    List<DepartmentDTO> selectByExample(DepartmentExample example);

    DepartmentDTO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}