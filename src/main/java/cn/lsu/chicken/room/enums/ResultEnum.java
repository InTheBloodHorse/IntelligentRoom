package cn.lsu.chicken.room.enums;

public enum ResultEnum {
    PHONE_EXITS(1, "手机号已被注册"),
    PARAMETER_ERROR(2, "参数不正确"),
    USER_NOT_EXITS(3, "用户不存在"),
    PASSWORD_ERROR(4, "密码错误"),
    TAG_IS_EXITS(5, "标签已存在"),
    TAG_NOT_EXITS(6, "标签不存在"),
    MEETING_ROOM_NOT_EXITS(7, "会议室不存在"),
    BUILDING_IS_EXITS(8, "楼幢已存在"),
    BUILDING_NOT_EXITS(9, "楼幢不存在"),
    COMPANY_NOT_EXITS(10, "公司不存在"),
    COMPANY_IS_EXITS(11, "公司已存在"),
    USER_NOT_EXITS_OR_PASSWORD_ERROR(12, "用户名或密码不正确"),
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
