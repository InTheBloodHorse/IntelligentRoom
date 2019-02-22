package cn.lsu.chicken.room.form.user;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import cn.lsu.chicken.room.myAnnotations.Phone;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.List;

@Data
public class UserQueryForm extends BaseQueryForm {
    private String name;

    @Phone
    private String phone;

    @Email
    private String email;


    private Integer role;

    private Integer companyId;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.LIKE,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL
    );
}
