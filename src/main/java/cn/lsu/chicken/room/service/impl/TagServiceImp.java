package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.TagRepository;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        if (tagRepository.findByName(tag.getName()) != null) {
            throw new GlobalException(ResultEnum.TAG_IS_EXITS);
        }
        Tag result = tagRepository.save(tag);
        return result;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(Integer id) {
        try {
            tagRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.TAG_NOT_EXITS);
        }
    }
}
