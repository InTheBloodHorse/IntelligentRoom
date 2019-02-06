package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.TagRepository;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.entity.mapper.TagMapper;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void saveTag(Tag tag) {
        if (tag.getId() == null) {
            if (tagRepository.existsByName(tag.getName()) == true) {
                throw new GlobalException(ResultEnum.TAG_IS_EXITS);
            }
        } else {
            Tag result = tagRepository.findById(tag.getId()).orElse(null);
            if (result == null) {
                throw new GlobalException(ResultEnum.TAG_NOT_EXITS);
            }
            if (!tag.getName().equals(result.getName())) {
                if (tagRepository.existsByName(tag.getName()) == true) {
                    throw new GlobalException(ResultEnum.TAG_IS_EXITS);
                }
            }
        }
        tagRepository.save(tag);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public PageDTO<Tag> listTagByPage(PageHelper pageHelper) {
        List<Tag> data = tagMapper.listTagByPage(pageHelper);
        Long total = tagMapper.count();
        PageDTO<Tag> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    @Override
    public void deleteTag(String id) {
        try {
            tagRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.TAG_NOT_EXITS);
        }
    }
}
