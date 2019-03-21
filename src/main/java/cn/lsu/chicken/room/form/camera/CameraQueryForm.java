package cn.lsu.chicken.room.form.camera;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CameraQueryForm extends BaseQueryForm {
    private Integer id;
    private Integer meetingRoomId;
    private Integer meetingApplyId;
    private Integer userId;
    private String createTime;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.BETWEEN_DATE
    );
}
