package cn.lsu.chicken.room.enums;

public enum QueryFormEnum {
    IGNORE(0,"ignore"),
    EQUAL(1, "EqualTo"),
    LIKE(2, "Like"),
    BETWEEN_INTEGER(3,"Between"),
    BETWEEN_BIGDECIMAL(4,"Between"),
    BETWEEN_DATE(5,"Between");
    private Integer code;
    private String operator;

    QueryFormEnum(Integer code, String operator) {
        this.code = code;
        this.operator = operator;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
