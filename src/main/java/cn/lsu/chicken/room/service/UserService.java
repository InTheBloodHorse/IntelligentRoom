package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.dto.UserFaceDTO;
import cn.lsu.chicken.room.form.user.UserQueryByIdForm;
import cn.lsu.chicken.room.form.user.UserQueryForm;

import java.util.List;

public interface UserService extends BaseService<UserDTO, User, Integer, UserQueryForm> {

    /*
    登录
    1：帐号 密码登录
    2：人脸登录（未完成）
     */
    UserDTO login(String phone, String password);

    // 根据手机号码查找用户信息
    UserDTO getUserByPhone(String phone);


    // 查询会议订单的所有用户
    PageDTO<User> listUserByApplyId(UserQueryByIdForm userQueryByIdForm);

    Integer uploadBySelective(User user);

//    //得到人脸特征值
//    UserFaceDTO getUserFace();

}
