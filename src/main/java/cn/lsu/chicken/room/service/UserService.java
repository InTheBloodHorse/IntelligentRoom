package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.User;

public interface UserService {

    /*
    注册用户，手机号码唯一
     */
    User registerUser(User user);
}
