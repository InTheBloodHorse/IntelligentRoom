package cn.lsu.chicken.room.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    private Integer id;

    private String name;

    private String code;

    private Long cost;

}