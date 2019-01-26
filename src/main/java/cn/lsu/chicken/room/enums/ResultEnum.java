package cn.lsu.chicken.room.enums;

public enum ResultEnum {
    PHONE_EXITS(1, "手机号已被注册"),
    PARAMETER_ERROR(2, "参数不正确"),
    SOMETHING_ERROR(500,"系统错误，请重试");

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
