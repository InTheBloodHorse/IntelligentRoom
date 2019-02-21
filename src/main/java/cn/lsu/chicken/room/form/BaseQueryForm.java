package cn.lsu.chicken.room.form;

import lombok.Data;

@Data
public class BaseQueryForm {
    private Integer page = 0;

    private Integer size = 0;

    private String order;
}
