package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.dao.CompanyRepository;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.form.UpdateForm;
import cn.lsu.chicken.room.form.UserForm;
import org.springframework.beans.BeanUtils;

public class UserForm2User {


    public static User change(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        return user;
    }

    public static User change(UpdateForm updateForm) {

        User user = new User();
        BeanUtils.copyProperties(updateForm, user);
        return user;
    }
}
