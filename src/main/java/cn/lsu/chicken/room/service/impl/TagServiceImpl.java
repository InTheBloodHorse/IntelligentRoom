package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.entity.mapper.TagMapper;
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
    public void saveEntity(Tag tag) {
        String name = tag.getName();
        judgeExistsByName(name);
        tagMapper.addEntity(tag);
    }

    @Override
    public void updateEntity(Tag tag) {
        String id = tag.getId();
        String name = tag.getName();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        //假如当前标签修改了名称
        if (!tagMapper.judgeExistsByIdAndName(id, name)) {
            //校验名称是否合法
            judgeExistsByName(name);
        }

        tagMapper.updateEntity(tag);
    }

    @Override
    public List<Tag> listEntity() {
        return tagMapper.listEntity();
    }

    @Override
    public PageDTO<Tag> listEntityByPage(PageHelper pageHelper) {
        List<Tag> data = tagMapper.listEntityByPage(pageHelper);
        Long total = tagMapper.count();
        PageDTO<Tag> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    @Override
    public void deleteEntity(String id) {
        tagMapper.deleteEntityById(id);
    }


    @Override
    public Tag getEntityById(String s) {
        return tagMapper.getEntity(s);
    }


    private void judgeExistsByName(String name) {
        Boolean result = tagMapper.judgeExistsByName(name);
        if (result) {
            throw new GlobalException(ResultEnum.TAG_IS_EXITS);
        }
    }
}
