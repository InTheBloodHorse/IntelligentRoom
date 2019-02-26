package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.UserMapper;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.domain.UserExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.user.UserQueryByIdForm;
import cn.lsu.chicken.room.form.user.UserQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.security.DecryptMD5;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer saveEntity(User entity) {
        String phone = entity.getPhone();
        entity.setPassword(DecryptMD5.MD5(entity.getPassword()));
        judgeExistByPhone(phone);
        userMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(User entity) {
        Integer id = entity.getId();
        String phone = entity.getPhone();
        Boolean judgePhone = phone != null && judgeExistByIdAndPhone(id, phone) == false;
        if (judgePhone) {
            judgeExistByPhone(phone);
        }
        String password = entity.getPassword();
        if (password != null) {
            entity.setPassword(DecryptMD5.MD5(password));
        }
        return userMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return userMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public UserDTO getEntityById(Integer integer) {
        return userMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<UserDTO> listEntityByQueryForm(UserQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        UserExample userExample = (UserExample) QueryFormUtil.getExample(UserExample.class, page, size, order);
        UserExample.Criteria criteria = userExample.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, UserQueryForm.QUERTFORMLIST);
        List<UserDTO> data = userMapper.selectByExample(userExample);
        Integer total = userMapper.countByExample(userExample);
        PageHelper pageHelper = userExample;
        PageDTO<UserDTO> userDTOPageDTO = new PageDTO<>(pageHelper, total, data);
        return userDTOPageDTO;
    }


    @Override
    public UserDTO login(String phone, String password) {
        List<UserDTO> userList = userMapper.selectByExample(getExampleByPhone(phone));
        if (userList.size() == 0) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS_OR_PASSWORD_ERROR);
        }
        UserDTO user = userList.get(0);
        Boolean result = DecryptMD5.judge(user.getPassword(), password);
        if (result == false) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS_OR_PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<UserDTO> userList = userMapper.selectByExample(userExample);
        UserDTO result = null;
        if (userList.size() == 1) {
            result = userList.get(0);
        }
        return result;
    }

    @Override
    public PageDTO<User> listUserByApplyId(UserQueryByIdForm userQueryByIdForm) {
        Integer page = userQueryByIdForm.getPage();
        Integer size = userQueryByIdForm.getSize();
        String order = userQueryByIdForm.getOrder();
        UserExample userExample = (UserExample) QueryFormUtil.getExample(UserExample.class, page, size, order);
        Integer applyId = userQueryByIdForm.getId();
        List<User> data = userMapper.selectByApplyIdExample(applyId, userExample);
        Integer total = userMapper.countByApplyIdExample(applyId);
        PageHelper pageHelper = userExample;
        PageDTO<User> userDTOPageDTO = new PageDTO<>(pageHelper, total, data);
        return userDTOPageDTO;
    }


    @Override
    public Integer uploadBySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }


    private UserExample getExampleByPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return userExample;
    }

    private void judgeExistByPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        Integer result = userMapper.countByExample(userExample);
        if (result > 0) {
            throw new GlobalException(ResultEnum.PHONE_EXIST);
        }
    }

    private Boolean judgeExistByIdAndPhone(Integer id, String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andPhoneEqualTo(phone);
        Integer result = userMapper.countByExample(userExample);
        return result > 0 ? true : false;
    }
}
