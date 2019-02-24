package cn.lsu.chicken.room.form;

import cn.lsu.chicken.room.enums.UserRoleEnum;
import cn.lsu.chicken.room.myannotations.Phone;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, message = "用户名长度最小为2")
    private String name;

    @NotBlank(message = "手机号不能为空")
    @Phone
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度在6到20之间")
    private String password;

    private Integer role = UserRoleEnum.ORDINARY_USER.getCode();

    private String companyCode;
}
