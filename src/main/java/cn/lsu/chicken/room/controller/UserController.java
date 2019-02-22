package cn.lsu.chicken.room.controller;


import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.convert.UserForm2User;
import cn.lsu.chicken.room.domain.Tag;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.enums.LoginEnum;
import cn.lsu.chicken.room.enums.OSSTypeEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.LoginForm;
import cn.lsu.chicken.room.form.RegisterForm;
import cn.lsu.chicken.room.form.UpdateForm;
import cn.lsu.chicken.room.form.user.UserQueryForm;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.HttpUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


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
    public ResultVO<Integer> register(@Valid @RequestBody RegisterForm registerForm,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册帐号,参数不正确,registerForm={}", registerForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        User entity = UserForm2User.change(registerForm);
        String code = registerForm.getCompanyCode();
        if (code != null) {
            Integer companyId = companyService.getCompanyIdByCode(code);
            entity.setCompanyId(companyId);
        }
        Integer userId = userService.saveEntity(entity);
        return ResultVOUtil.success(userId);
    }

    @PostMapping("/login")
    public ResultVO<UserDTO> login(@Valid @RequestBody LoginForm loginForm,
                                   BindingResult bindingResult) {
        // phone 手机登录
        UserDTO user = new UserDTO();
        String type = loginForm.getType();
        if (LoginEnum.PHONE.getType().equals(type)) {
            if (bindingResult.hasErrors()) {
                log.error("手机登录,参数不正确,loginForm={}", loginForm);
                throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
            }

            String phone = loginForm.getPhone();
            String password = loginForm.getPassword();
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

    @PostMapping("/uploadHeadImage")
    public ResultVO<String> uploadHeadImage(@RequestParam("id") Integer id,
                                            @RequestParam("file") MultipartFile file) {

        Long time1 = System.currentTimeMillis();
        String url = HttpUtil.uploadFile(file, OSSTypeEnum.HEAD.getCode());
        System.out.println(System.currentTimeMillis() - time1);
        User user = new User();
        user.setId(id);
        user.setAvatar(url);
        userService.uploadBySelective(user);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/updateInfo")
    public ResultVO<UserDTO> updateInfo(@Valid @RequestBody UpdateForm updateForm,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,updateForm={}", updateForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        User user = UserForm2User.change(updateForm);
        userService.updateEntity(user);
        return ResultVOUtil.success();
    }

    @PostMapping("/getUser")
    public ResultVO<UserDTO> getUser(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        UserDTO userDTO = userService.getEntityById(id);
        return ResultVOUtil.success(userDTO);
    }

    @PostMapping("/getUserByPhone")
    public ResultVO<UserDTO> getUserByPhone(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        String phone = params.get("phone").getAsString();
        if (phone == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        UserDTO userDTO = userService.getUserByPhone(phone);
        return ResultVOUtil.success(userDTO);
    }

    @PostMapping("/listUser")
    public ResultVO<PageDTO<UserDTO>> listUser(@Valid @RequestBody UserQueryForm userQueryForm,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,userQueryForm={}", userQueryForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        PageDTO<UserDTO> data = userService.listEntityByQueryForm(userQueryForm);
        return ResultVOUtil.success(data);
    }

}
