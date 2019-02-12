package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.TagReMapper;
import cn.lsu.chicken.room.domain.TagRe;
import cn.lsu.chicken.room.domain.TagReExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.TagReService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagReServiceImpl implements TagReService {

    @Autowired
    private TagReMapper tagReMapper;

    @Override
    public Integer saveEntity(TagRe entity) {
        judgeExistByTagIdAndMeetingRoomId(entity.getTagId(), entity.getMeetingRoomId());
        tagReMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(TagRe entity) {
        return null;
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return null;
    }

    @Override
    public TagRe getEntityById(Integer integer) {
        return null;
    }

    @Override
    public List<TagRe> listEntity() {
        return null;
    }

    @Override
    public PageDTO<TagRe> listEntityByPage(PageHelper pageHelper) {
        return null;
    }

    private void judgeExistByTagIdAndMeetingRoomId(Integer tagId, Integer meetingRoomId) {
        TagReExample tagReExample = new TagReExample();
        TagReExample.Criteria criteria = tagReExample.createCriteria();
        criteria.andTagIdEqualTo(tagId);
        criteria.andMeetingRoomIdEqualTo(meetingRoomId);
        List<TagRe> result = tagReMapper.selectByExample(tagReExample);
        if (result.size() != 0) {
            throw new GlobalException(ResultEnum.TAG_HAS_ADDED);
        }
    }
}
