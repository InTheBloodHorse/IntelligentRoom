package cn.lsu.chicken.room.form.meetingroom;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class MeetingRoomQueryForm extends BaseQueryForm {
    private Integer id;
    private String name;
    private Integer buildingId;
    // 格式 a,b  代表  区间[a,b]
    private String volume;
    private Integer isUsing;
    // 格式 a,b  代表  区间[a,b]
    private String price;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.LIKE,
            QueryFormEnum.EQUAL,
            QueryFormEnum.BETWEEN_INTEGER,
            QueryFormEnum.EQUAL,
            QueryFormEnum.BETWEEN_BIGDECIMAL
    );
}
