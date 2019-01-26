package cn.lsu.chicken.room.controller;


import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.convert.RegisterFrom2User;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.UserException;
import cn.lsu.chicken.room.form.RegisterForm;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UseController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultVO<User> register(@Valid RegisterForm registerForm,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册帐号,参数不正确,registerForm={}", registerForm);
            throw new UserException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        User user = userService.registerUser(RegisterFrom2User.change(registerForm));
        return ResultVOUtil.success(user);
    }

}
