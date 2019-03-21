package cn.lsu.chicken.room.enums;

public enum OSSTypeEnum {
    SYSTEM("0", "系统资料"),
    HEAD("1", "HEAD"),
    RESOURCES("2", "会议资料"),
    FACE("3", "人脸图片");
    private String code;
    private String type;

    OSSTypeEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
