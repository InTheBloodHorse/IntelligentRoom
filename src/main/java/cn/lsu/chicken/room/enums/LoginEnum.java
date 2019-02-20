package cn.lsu.chicken.room.enums;

import lombok.Data;

public enum LoginEnum {
    PHONE("phone"),
    FACE("face");

    private String type;

    LoginEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
