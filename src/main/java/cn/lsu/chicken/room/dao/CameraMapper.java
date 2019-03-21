package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.Camera;
import cn.lsu.chicken.room.domain.CameraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CameraMapper {
    int countByExample(CameraExample example);

    int deleteByExample(CameraExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Camera record);

    int insertSelective(Camera record);

    List<Camera> selectByExample(CameraExample example);

    Camera selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Camera record, @Param("example") CameraExample example);

    int updateByExample(@Param("record") Camera record, @Param("example") CameraExample example);

    int updateByPrimaryKeySelective(Camera record);

    int updateByPrimaryKey(Camera record);
}