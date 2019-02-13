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
        tagReMapper.insertSelective(entity);
        return entity.getId();
    }

    @Deprecated
    @Override
    public Integer updateEntity(TagRe entity) {
        return null;
    }

    @Override
    public Integer updateEntity(Integer roomId, List<Integer> tagList) {
        return tagReMapper.updateTagReByListExample(roomId, tagList);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return tagReMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public TagRe getEntityById(Integer integer) {
        return tagReMapper.selectByPrimaryKey(integer);
    }

    @Override
    public List<TagRe> listEntity() {
        return tagReMapper.selectByExample(new TagReExample());
    }

    @Override
    public PageDTO<TagRe> listEntityByPage(PageHelper pageHelper) {
        TagReExample tagReExample = new TagReExample(pageHelper.getPage(), pageHelper.getSize());
        List<TagRe> data = tagReMapper.selectByExample(tagReExample);
        Integer total = tagReMapper.countByExample(new TagReExample());
        PageDTO<TagRe> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
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

    @Override
    public Integer deleteByTagIdAndRoomId(Integer tagId, Integer roomId) {
        TagReExample tagReExample = new TagReExample();
        TagReExample.Criteria criteria = tagReExample.createCriteria();
        criteria.andTagIdEqualTo(tagId);
        criteria.andMeetingRoomIdEqualTo(roomId);
        return tagReMapper.deleteByExample(tagReExample);
    }

}
