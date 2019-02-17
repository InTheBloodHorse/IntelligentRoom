package cn.lsu.chicken.room.helper;

import lombok.Data;

@Data
@Deprecated
public class ExampleHelper {
    String Logic;
    String field;
    String operator;
    Object[] args;

    public ExampleHelper(String logic, String field, String operator, Object... args) {
        Logic = logic;
        this.field = field;
        this.operator = operator;
        this.args = args;
    }
}
