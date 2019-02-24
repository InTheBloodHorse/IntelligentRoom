package cn.lsu.chicken.room.form;


import cn.lsu.chicken.room.myannotations.Phone;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateForm {
    @NotNull(message = "编号不能为空")
    private Integer id;

    @Size(min = 2, message = "用户名长度最小为2")
    private String name;

    @Phone
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Size(min = 6, max = 20, message = "密码长度在6到20之间")
    private String password;

    private Integer role;

    private Integer companyId;

}
