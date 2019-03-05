package cn.lsu.chicken.room.form.department;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class DepartmentQueryForm extends BaseQueryForm {
    private Integer id;
    private String name;
    private Integer companyId;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.LIKE,
            QueryFormEnum.EQUAL
    );
}
