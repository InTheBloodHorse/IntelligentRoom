package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.helper.PageHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TagService {

    //插入 修改
    void saveTag(Tag tag);

    void deleteTag(String id);

    List<Tag> listTag();

    PageDTO<Tag> listTagByPage(PageHelper pageHelper);
}
