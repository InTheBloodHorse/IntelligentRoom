package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.TagMapper;
import cn.lsu.chicken.room.domain.Tag;
import cn.lsu.chicken.room.domain.TagExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Integer saveEntity(Tag tag) {
        String name = tag.getName();
        judgeExistByName(name);
        tagMapper.insertSelective(tag);
        return tag.getId();
    }

    @Override
    public Integer updateEntity(Tag tag) {

        Integer id = tag.getId();
        String name = tag.getName();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        //假如当前标签修改了名称

        if (judgeExistByIdAndName(id, name) == false) {
            //校验名称是否合法
            judgeExistByName(name);
        }

        return tagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return tagMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public Tag getEntityById(Integer integer) {
        return tagMapper.selectByPrimaryKey(integer);
    }

    @Override
    public List<Tag> listEntity() {
        return tagMapper.selectByExample(new TagExample());
    }

    @Override
    public PageDTO<Tag> listEntityByPage(PageHelper pageHelper) {
        TagExample tagExample = new TagExample(pageHelper.getPage(), pageHelper.getSize());
        List<Tag> data = tagMapper.selectByExample(tagExample);
        Integer total = tagMapper.countByExample(new TagExample());
        PageDTO<Tag> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }


    private void judgeExistByName(String name) {
        TagExample tagExample = new TagExample();
        TagExample.Criteria criteria = tagExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Tag> result = tagMapper.selectByExample(tagExample);
        if (result.size() != 0) {
            throw new GlobalException(ResultEnum.TAG_IS_EXIST);
        }
    }

    private Boolean judgeExistByIdAndName(Integer id, String name) {
        TagExample tagExample = new TagExample();
        TagExample.Criteria criteria = tagExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andNameEqualTo(name);
        List<Tag> result = tagMapper.selectByExample(tagExample);
        if (result.size() == 0) {
            return false;
        }
        return true;
    }
}
