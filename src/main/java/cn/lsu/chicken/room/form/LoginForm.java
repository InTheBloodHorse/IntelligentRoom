package cn.lsu.chicken.room.form;

import cn.lsu.chicken.room.myAnnotations.Phone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    @NotBlank
    private String type;

    @Phone
    private String phone;

    private String password;

    // 还有人脸信息字段
    private Object face;
}
