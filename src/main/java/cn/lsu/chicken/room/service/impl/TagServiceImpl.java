package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.TagRepository;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

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
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
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
