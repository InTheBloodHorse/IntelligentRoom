package cn.lsu.chicken.room.entity.mapper;

import cn.lsu.chicken.room.entity.Building;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface BuildingMapper extends BaseMapper<Building, String> {
    Boolean judgeExistsByLocation(String location);

    Boolean judgeExistsByIdAndLocation(@Param("id")String id,@Param("location")String location);
}
