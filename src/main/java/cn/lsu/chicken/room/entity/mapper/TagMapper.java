package cn.lsu.chicken.room.entity.mapper;


import cn.lsu.chicken.room.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface TagMapper extends BaseMapper<Tag, String> {


    Boolean judgeExistsByIdAndName(@Param("id") String id, @Param("name") String name);

    Boolean judgeExistsByName(String name);
}
