package cn.lsu.chicken.room.form.weight;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class WeightQueryForm extends BaseQueryForm {
    Integer id;
    Integer companyId;
    String updateTime;
    String delayTime;
    String cancelTime;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.BETWEEN_INTEGER,
            QueryFormEnum.BETWEEN_INTEGER,
            QueryFormEnum.BETWEEN_INTEGER
    );
}
