package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.entity.User;

import java.util.List;

public interface UserService {

    /*
    注册用户，手机号码唯一
     */
    UserDTO registerUser(User user);

    /*
    更新用户信息
     */
    UserDTO updateUser(User user);

    /*
    登录
    1：帐号 密码登录
    2：人脸登录（未完成）
     */
    UserDTO login(String phone, String password);

    //根据ID查找用户信息
    UserDTO getUserById(Integer id);

    //根据手机号码查找用户信息
    UserDTO getUserByPhone(String phone);

    //根据公司编号查找员工信息
    List<UserDTO> getUsersByCompany(Integer companyId);

    //删除员工
    Boolean deleteUser(Integer id);

    //查询会议订单的所有用户
    List<User> findByApplyId(String applyId);
}
