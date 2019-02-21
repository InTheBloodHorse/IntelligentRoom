package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.constant.QueryFormConstant;
import cn.lsu.chicken.room.dao.UserMapper;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.domain.UserExample;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.UserQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.security.DecryptMD5;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<UserDTO> listEntity() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public PageDTO<UserDTO> listEntityByPage(PageHelper pageHelper) {
        UserExample userExample = new UserExample(pageHelper.getPage(), pageHelper.getSize());
        List<UserDTO> data = userMapper.selectByExample(userExample);
        Integer total = userMapper.countByExample(new UserExample());
        PageDTO<UserDTO> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
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
    public List<UserDTO> listUserByCompany(Integer companyId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        List<UserDTO> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public List<UserDTO> findByApplyId(String applyId) {
        return null;
    }

    @Override
    public Integer uploadBySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageDTO<UserDTO> listUserByQueryForm(UserQueryForm userQueryForm) {
        Integer page = userQueryForm.getPage();
        Integer size = userQueryForm.getSize();
        UserExample userExample = (UserExample) QueryFormUtil.getExample(UserExample.class, page, size);
        String order = userQueryForm.getOrder();
        userExample.setOrderList(order);
        UserExample.Criteria criteria = userExample.createCriteria();
        QueryFormUtil.addFilter(criteria, userQueryForm, QueryFormConstant.USERQUERTFORMLIST);
        System.out.println(userMapper.selectByExample(userExample));
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

    private Boolean judgeExistByIdAndPhone(Integer id, String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andPhoneEqualTo(phone);
        Integer result = userMapper.countByExample(userExample);
        return result > 0 ? true : false;
    }
}
