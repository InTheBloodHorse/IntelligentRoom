package cn.lsu.chicken.room.form;

import cn.lsu.chicken.room.myAnnotations.Phone;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateForm {
    @NotNull
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, message = "用户名长度最小为3")
    private String name;

    @NotBlank(message = "手机号不能为空")
    @Phone
    private String phone;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer role;

    private Integer companyId;

}
