package cn.lsu.chicken.room.form.service;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ServiceQueryForm extends BaseQueryForm {

    private Integer id;

    private Integer applyId;

    private Integer workerId;

    private String content;

    private String applyTime;

    private String serviceTime;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.LIKE,
            QueryFormEnum.BETWEEN_DATE,
            QueryFormEnum.BETWEEN_DATE
    );
}
