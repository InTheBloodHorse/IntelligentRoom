package cn.lsu.chicken.room.form.meetingapply;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class MeetingApplyQueryForm extends BaseQueryForm {

    private Integer id;
    // 申请员工
    private Integer workerId;

    private Integer roomId;

    private String attendance;

    //开会时间 根据开始时间来判定
    private String beginTime;

    private Integer status;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.BETWEEN_INTEGER,
            QueryFormEnum.BETWEEN_DATE,
            QueryFormEnum.EQUAL
    );
}
