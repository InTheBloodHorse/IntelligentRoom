package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.TagRe;
import cn.lsu.chicken.room.domain.TagReExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface TagReMapper {
    int countByExample(TagReExample example);

    int deleteByExample(TagReExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagRe record);

    int insertSelective(TagRe record);

    List<TagRe> selectByExample(TagReExample example);

    TagRe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagRe record, @Param("example") TagReExample example);

    int updateByExample(@Param("record") TagRe record, @Param("example") TagReExample example);

    int updateByPrimaryKeySelective(TagRe record);

    int updateByPrimaryKey(TagRe record);
}