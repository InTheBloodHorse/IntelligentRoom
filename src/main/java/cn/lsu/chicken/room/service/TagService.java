package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.Tag;

import java.util.List;

public interface TagService {

    //插入 修改
    Tag saveTag(Tag tag);

    List<Tag> getAllTags();

    void deleteTag(String id);

}
