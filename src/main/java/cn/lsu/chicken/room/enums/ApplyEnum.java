package cn.lsu.chicken.room.enums;

public enum ApplyEnum {
    WRONG(-1),
    BE_APPLY(0);
    private Integer code;

    ApplyEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
