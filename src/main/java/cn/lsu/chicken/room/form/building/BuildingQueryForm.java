package cn.lsu.chicken.room.form.building;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class BuildingQueryForm extends BaseQueryForm {
    private Integer id;
    private String location;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL
    );

}
