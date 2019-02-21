package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.form.RegisterForm;
import cn.lsu.chicken.room.form.UpdateForm;
import org.springframework.beans.BeanUtils;

public class UserForm2User {


    public static User change(RegisterForm registerForm) {
        User user = new User();
        BeanUtils.copyProperties(registerForm, user);
        return user;
    }

    public static User change(UpdateForm updateForm) {

        User user = new User();
        BeanUtils.copyProperties(updateForm, user);
        return user;
    }
}
