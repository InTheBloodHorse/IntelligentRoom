package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    //插入 修改
    void saveTag(Tag tag);

    void deleteTag(String id);

    List<Tag> getAllTags();

    Page<Tag> getAllTags(Pageable pageable);
}
