package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.TagMapper;
import cn.lsu.chicken.room.domain.Tag;
import cn.lsu.chicken.room.domain.TagExample;
import cn.lsu.chicken.room.dto.PageDTO;
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
        tagMapper.insert(tag);
    }

    @Override
    public void updateEntity(Tag tag) {
//        String id = tag.getId();
//        String name = tag.getName();
//        if (id == null) {
//            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
//        }
//        //假如当前标签修改了名称
//
//        if (!tagMapper.judgeExistsByIdAndName(id, name)) {
//            //校验名称是否合法
//            judgeExistsByName(name);
//        }
//
//        tagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public List<Tag> listEntity() {
        return tagMapper.selectByExample(new TagExample());
    }

    @Override
    public PageDTO<Tag> listEntityByPage(PageHelper pageHelper) {
//        List<Tag> data = tagMapper.listEntityByPage(pageHelper);
//        Long total = tagMapper.count();
//        PageDTO<Tag> pageDTO = new PageDTO<>(pageHelper, total, data);
//        return pageDTO;
        return null;
    }

    @Override
    public void deleteEntity(String id) {


    }


    @Override
    public Tag getEntityById(String s) {
        return tagMapper.selectByPrimaryKey(s);
    }


    private void judgeExistsByName(String name) {
//        Boolean result = tagMapper.judgeExistsByName(name);
//        if (result) {
//            throw new GlobalException(ResultEnum.TAG_IS_EXITS);
//        }
    }
}
