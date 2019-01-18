package cn.lsu.chicken.room.enums;


public enum UserRoleEnum {
    ORDINARY_USER(1, "普通用户"),
    ADMIN(2, "普通管理员"),
    SUPER_ADMIN(3, "超级管理员");
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
