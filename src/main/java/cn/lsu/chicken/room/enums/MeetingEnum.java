package cn.lsu.chicken.room.enums;

public enum MeetingEnum {
    NOT_ARRANGE(0, "未安排"),
    ARRANGE(1, "安排")
    ;
    private Integer code;
    private String name;

    MeetingEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
