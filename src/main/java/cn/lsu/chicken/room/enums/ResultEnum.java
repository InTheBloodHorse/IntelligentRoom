package cn.lsu.chicken.room.enums;

public enum ResultEnum {
    PHONE_EXIST(1, "手机号已被注册"),
    PARAMETER_ERROR(2, "参数不正确"),
    USER_NOT_EXIST(3, "用户不存在"),
    PASSWORD_ERROR(4, "密码错误"),
    TAG_IS_EXIST(5, "标签已存在"),
    TAG_NOT_EXIST(6, "标签不存在"),
    MEETING_ROOM_NOT_EXIST(7, "会议室不存在"),
    BUILDING_IS_EXIST(8, "楼幢已存在"),
    BUILDING_NOT_EXIST(9, "楼幢不存在"),
    COMPANY_NOT_EXIST(10, "公司不存在"),
    COMPANY_IS_EXIST(11, "公司已存在"),
    USER_NOT_EXITS_OR_PASSWORD_ERROR(12, "用户名或密码不正确"),
    APPLY_NOT_EXIST(13, "预约单不存在"),
    WRONG_DATE_STR(14, "日期错误"),
    MEETING_ROOM_IS_EXIST(15, "会议室已存在"),
    TAG_HAS_ADDED(16, "请勿重复添加标签"),
    FILE_IS_EMPTY(17, "文件不能为空"),
    FILE_UPLOAD_ERROR(18, "文件上传失败，请重试"),
    DATE_IS_WRONG(19, "日期格式错误—— 年-月-日 时:分"),
    DATE_IS_INVALID(20, "日期不合法"),
    ID_NEED(21, "编号不能为空"),
    STR_CAN_NOT_NULL(22, "内容不能为空"),
    SOMETHING_ERROR(500, "系统错误，请重试");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
