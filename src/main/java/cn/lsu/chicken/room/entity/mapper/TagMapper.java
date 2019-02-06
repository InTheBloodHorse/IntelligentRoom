package cn.lsu.chicken.room.entity.mapper;

import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.helper.PageHelper;


import java.util.List;
import java.util.Map;

public interface TagMapper {

    void addTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTagById(String tagId);

    Tag getTag(String tagId);

    List<Tag> listTag();

    List<Tag> listTagByPage(PageHelper pageHelper);

    Long count();

    Boolean judgeExistsByName(String name);
}
