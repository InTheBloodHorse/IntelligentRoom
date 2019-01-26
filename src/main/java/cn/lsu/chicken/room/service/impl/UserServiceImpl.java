package cn.lsu.chicken.room.service.impl;


import cn.lsu.chicken.room.dao.UserRepository;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.UserException;
import cn.lsu.chicken.room.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.findFirstByPhone(user.getPhone()) != null) {
            log.error("注册——手机号重复:{}", user.getPhone());
            throw new UserException(ResultEnum.PHONE_EXITS);
        }
        User result = userRepository.save(user);
        return result;
    }
}
