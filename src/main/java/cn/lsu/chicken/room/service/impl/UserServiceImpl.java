package cn.lsu.chicken.room.service.impl;


import cn.lsu.chicken.room.dao.UserRepository;
import cn.lsu.chicken.room.dto.UserDTO;
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
    @Autowired
    private CompanyService companyService;

    @Override
    public UserDTO registerUser(User user) {
        if (userRepository.findFirstByPhone(user.getPhone()) != null) {
            log.error("注册——手机号重复:{}", user.getPhone());
            throw new GlobalException(ResultEnum.PHONE_EXITS);
        }
        User result = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(result, userDTO);
        userDTO.setCompany(companyService.findCompanyById(user.getCompanyId()));
        return userDTO;
    }

    @Override
    public UserDTO updateUser(User user) {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (oldUser == null) {
            log.error("更新帐号信息——用户不存在:{}", user);
            throw new GlobalException(ResultEnum.USER_NOT_EXITS);
        }
        if (!user.getPhone().equals(oldUser.getPhone()) && userRepository.findFirstByPhone(user.getPhone()) != null) {
            log.error("注册——手机号重复:{}", user);
            throw new GlobalException(ResultEnum.PHONE_EXITS);
        }
        User result = userRepository.save(user);
        return null;
    }

    @Override
    public UserDTO login(String phone, String password) {
        User user = userRepository.findFirstByPhone(phone);
        if (user == null) {
            log.error("用户不存在:{}", phone);
            throw new GlobalException(ResultEnum.USER_NOT_EXITS);
        }

        if (DecryptMD5.judge(user.getPassword(), password) == false) {
            log.error("密码错误:user={},password={}", user, password);
            throw new GlobalException(ResultEnum.PASSWORD_ERROR);
        }
        return null;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return null;
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        return null;
    }

    @Override
    public List<UserDTO> getUsersByCompany(Integer companyId) {
        return null;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return null;
    }

    @Override
    public List<User> findByApplyId(String applyId) {
        return null;
    }

}
