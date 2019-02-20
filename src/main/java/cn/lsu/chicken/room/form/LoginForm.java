package cn.lsu.chicken.room.form;

import cn.lsu.chicken.room.myAnnotations.Phone;
import lombok.Data;

@Data
public class LoginForm {

    @Phone
    private String phone;

    private String password;

    // 还有人脸信息字段
    private Object face;
}
