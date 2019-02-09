package cn.lsu.chicken.room.controller;


import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.convert.UserForm2User;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.UpdateForm;
import cn.lsu.chicken.room.form.UserForm;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResultVO<UserDTO> register(@Valid UserForm userForm,
//                                      BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            log.error("注册帐号,参数不正确,userForm={}", userForm);
//            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
//        }
//        UserDTO user = userService.registerUser(UserForm2User.change(userForm));
//        return ResultVOUtil.success(user);
//    }
//
//    @PostMapping("/login")
//    public ResultVO<User> login(@RequestParam(value = "phone") String phone,
//                                @RequestParam(value = "password") String password) {
//
//        return ResultVOUtil.success(userService.login(phone, password));
//    }
//
//    @PostMapping("/update")
//    public ResultVO<User> update(@Valid UpdateForm updateForm,
//                                 BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            log.error("注册帐号,参数不正确,userForm={}", updateForm);
//            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
//        }
//        userService.updateUser(UserForm2User.change(updateForm));
//        return ResultVOUtil.success();
//    }

}
