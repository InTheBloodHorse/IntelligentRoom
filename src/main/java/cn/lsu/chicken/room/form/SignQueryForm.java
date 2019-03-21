package cn.lsu.chicken.room.form;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class SignQueryForm extends BaseQueryForm {
    private Integer id;
    private Integer userId;
    private Integer applyId;
    private String signTime;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.BETWEEN_DATE
    );
}
