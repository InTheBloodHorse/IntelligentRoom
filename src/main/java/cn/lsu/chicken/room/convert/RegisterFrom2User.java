package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.form.RegisterForm;
import org.springframework.beans.BeanUtils;

public class RegisterFrom2User {
    public static User change(RegisterForm registerForm) {

        User user = new User();
        BeanUtils.copyProperties(registerForm, user);
        return user;
    }
}
