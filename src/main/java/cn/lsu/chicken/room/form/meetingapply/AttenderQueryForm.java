package cn.lsu.chicken.room.form.meetingapply;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Data
public class AttenderQueryForm extends BaseQueryForm {

    @NotNull(message = "用户编号不能为空")
    private Integer userId;

    private Integer status;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.IGNORE,
            QueryFormEnum.EQUAL
    );
}
