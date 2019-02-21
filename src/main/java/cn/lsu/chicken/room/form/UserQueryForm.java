package cn.lsu.chicken.room.form;

import cn.lsu.chicken.room.myAnnotations.Phone;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserQueryForm extends BaseQueryForm {
    private String name;

    @Phone
    private String phone;

    @Email
    private String email;


    private Integer role;

    private Integer companyId;

}
