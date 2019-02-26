package cn.lsu.chicken.room.form.file;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FileQueryForm extends BaseQueryForm {
    private Integer id;
    private String filename;
    private Integer uploadUserId;
    private Integer meetingRoomId;
    private Integer meetingApplyId;

    public static List<QueryFormEnum> QUERTFORMLIST = Arrays.asList(
            QueryFormEnum.EQUAL,
            QueryFormEnum.LIKE,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL,
            QueryFormEnum.EQUAL
    );

}
