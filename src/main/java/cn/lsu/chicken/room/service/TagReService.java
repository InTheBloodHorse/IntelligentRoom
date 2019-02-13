package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.TagRe;

import java.util.List;

public interface TagReService extends BaseService<TagRe, TagRe, Integer> {
    Integer deleteByTagIdAndRoomId(Integer tagId, Integer roomId);

    Integer updateEntity(Integer roomId, List<Integer> tagList);
}
