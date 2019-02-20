package cn.lsu.chicken.room.controller;


import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.convert.UserForm2User;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.enums.LoginEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.LoginForm;
import cn.lsu.chicken.room.form.UserForm;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    // 统一注册
    @PostMapping("/register")
    public ResultVO<Integer> register(@Valid UserForm userForm,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册帐号,参数不正确,userForm={}", userForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        User entity = UserForm2User.change(userForm);
        String code = userForm.getCompanyCode();
        if (code != null) {
            Integer companyId = companyService.getCompanyIdByCode(code);
            entity.setCompanyId(companyId);
        }
        Integer userId = userService.saveEntity(entity);
        return ResultVOUtil.success(userId);
    }

    @PostMapping("/login")
    public ResultVO<UserDTO> login(@RequestParam("type") String type,
                                   @Valid LoginForm loginForm,
                                   BindingResult bindingResult) {
        // phone 手机登录
        UserDTO user = new UserDTO();
        if (LoginEnum.PHONE.getType().equals(type)) {
            if (bindingResult.hasErrors()) {
                log.error("手机登录,参数不正确,loginForm={}", loginForm);
                throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
            }

            String phone = loginForm.getPhone();
            String password = loginForm.getPassword();
            System.out.println(loginForm);
            if (phone == null || password == null) {
                throw new GlobalException(ResultEnum.PARAMETER_ERROR);
            }
            user = userService.login(phone, password);
            if (user == null) {
                throw new GlobalException(ResultEnum.USER_NOT_EXITS_OR_PASSWORD_ERROR);
            }
        } else if (LoginEnum.FACE.getType().equals(type)) {
            System.out.println("现在是人脸登录");
            // TODO: 2019/2/20
        } else {
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return ResultVOUtil.success(user);
    }

    @GetMapping("/getUser")
    public ResultVO<UserDTO> getUser(@RequestParam("phone") String phone) {
        UserDTO userDTO = userService.getUserByPhone(phone);
        return ResultVOUtil.success(userDTO);
    }

}
