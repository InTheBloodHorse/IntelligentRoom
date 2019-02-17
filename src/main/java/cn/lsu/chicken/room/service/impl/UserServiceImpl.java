package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.convert.User2UserDTO;
import cn.lsu.chicken.room.dao.UserMapper;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.domain.UserExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.security.DecryptMD5;
import cn.lsu.chicken.room.service.UserService;
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
        return userMapper.insertSelective(entity);
    }

    @Override
    public Integer updateEntity(User entity) {
        return null;
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return null;
    }

    @Override
    public UserDTO getEntityById(Integer integer) {
        return null;
    }

    @Override
    public List<UserDTO> listEntity() {
        return null;
    }

    @Override
    public PageDTO<UserDTO> listEntityByPage(PageHelper pageHelper) {
        return null;
    }

    @Override
    public UserDTO login(String phone, String password) {
        List<UserDTO> userlist = userMapper.selectByExample(getExampleByPhone(phone));
        if (userlist.size() == 0) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS_OR_PASSWORD_ERROR);
        }
        UserDTO user = userlist.get(0);
        Boolean result = DecryptMD5.judge(user.getPassword(), password);
        if (result == false) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS_OR_PASSWORD_ERROR);
        }
//        System.out.println(user);
        return user;
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        return null;
    }

    @Override
    public List<UserDTO> listUserByCompany(Integer companyId) {
        return null;
    }

    @Override
    public List<UserDTO> findByApplyId(String applyId) {
        return null;
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
}
