package cn.lsu.chicken.room.service.impl;


import cn.lsu.chicken.room.convert.User2UserDTO;
import cn.lsu.chicken.room.dao.AttendWorkerRepository;
import cn.lsu.chicken.room.dao.UserRepository;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.entity.AttendWorker;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.security.DecryptMD5;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO registerUser(User user) {
        if (userRepository.existsByPhone(user.getPhone()) == true) {
            log.error("注册——手机号重复:{}", user.getPhone());
            throw new GlobalException(ResultEnum.PHONE_EXITS);
        }
        User result = userRepository.save(user);
        return User2UserDTO.convert(result);
    }

    @Override
    public UserDTO updateUser(User user) {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (oldUser == null) {
            log.error("更新帐号信息——用户不存在:{}", user);
            throw new GlobalException(ResultEnum.USER_NOT_EXITS);
        }
        if (!user.getPhone().equals(oldUser.getPhone()) && userRepository.existsByPhone(user.getPhone()) != null) {
            log.error("注册——手机号重复:{}", user);
            throw new GlobalException(ResultEnum.PHONE_EXITS);
        }
        User result = userRepository.save(user);
        return User2UserDTO.convert(result);
    }

    @Override
    public UserDTO login(String phone, String password) {
        UserDTO user = getUserByPhone(phone);
        if (DecryptMD5.judge(user.getPassword(), password) == false) {
            log.error("密码错误:user={},password={}", user, password);
            throw new GlobalException(ResultEnum.PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS);
        }
        return User2UserDTO.convert(user);
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        Object[][] user = userRepository.getUserByPhoneSql(phone);
        if (user == null) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS);
        }
        return User2UserDTO.convert(user);
    }

    @Override
    public List<UserDTO> listUsersByCompany(Integer companyId) {
        List<User> users = userRepository.findByCompanyId(companyId);
        return User2UserDTO.convert(users);
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.USER_NOT_EXITS);
        }
    }

    @Override
    public List<User> findByApplyId(String applyId) {
        return null;
    }

}
