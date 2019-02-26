package cn.lsu.chicken.room.form.user;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Data
public class UserQueryByIdForm extends BaseQueryForm {
    @NotNull(message = "编号不能为空")
    private Integer id;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL
    );
}
