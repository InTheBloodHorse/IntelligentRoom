package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.SignMapper;
import cn.lsu.chicken.room.domain.Sign;
import cn.lsu.chicken.room.domain.SignExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.form.SignQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.SignService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignServiceImpl implements SignService {

    @Autowired
    private SignMapper signMapper;

    @Override
    @Deprecated
    public Integer saveEntity(Sign entity) {
        return null;
    }

    @Override
    @Deprecated
    public Integer updateEntity(Sign entity) {
        return null;
    }

    @Override
    @Deprecated
    public Integer deleteEntity(Integer integer) {
        return null;
    }

    @Override
    @Deprecated
    public Sign getEntityById(Integer integer) {
        return null;
    }

    @Override
    public PageDTO<Sign> listEntityByQueryForm(SignQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        SignExample example = (SignExample) QueryFormUtil.getExample(SignExample.class, page, size, order);
        SignExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, SignQueryForm.QUERTFORMLIST);
        List<Sign> data = signMapper.selectByExample(example);
        Integer total = signMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<Sign> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }


    @Override
    public Integer addByApplyId(Integer applyId) {
        return signMapper.insertSignByMeetingApplyId(applyId);
    }

    @Override
    public Integer signSuccess(Integer applyId, Integer userId) {
        SignExample signExample = new SignExample();
        SignExample.Criteria criteria = signExample.createCriteria();
        criteria.andApplyIdEqualTo(applyId);
        criteria.andUserIdEqualTo(userId);
        Sign sign = new Sign();
        sign.setSignTime(new Date());
        Integer column = signMapper.updateByExampleSelective(sign, signExample);
        return column;
    }
}
