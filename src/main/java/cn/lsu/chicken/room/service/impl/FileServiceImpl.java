package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.FileMapper;
import cn.lsu.chicken.room.domain.File;
import cn.lsu.chicken.room.domain.FileExample;
import cn.lsu.chicken.room.domain.MeetingRoomExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.file.FileQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.FileService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Integer saveEntity(File entity) {
        fileMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(File entity) {
        return fileMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return fileMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public File getEntityById(Integer integer) {
        return fileMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<File> listEntityByQueryForm(FileQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        FileExample example = (FileExample) QueryFormUtil.getExample(FileExample.class, page, size, order);
        FileExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, FileQueryForm.QUERTFORMLIST);
        List<File> data = fileMapper.selectByExample(example);
        Integer total = fileMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<File> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }

}
