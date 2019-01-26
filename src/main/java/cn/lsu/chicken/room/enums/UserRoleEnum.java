package cn.lsu.chicken.room.enums;


public enum UserRoleEnum {
    ADMIN(1, "管理员"),
    HR(2, "公司HR"),
    SERVICE(3, "后勤人员"),
    ORDINARY_USER(4, "普通用户");
    private Integer code;
    private String name;

    UserRoleEnum(Integer code, String name) {
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
