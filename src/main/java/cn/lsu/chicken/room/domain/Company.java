package cn.lsu.chicken.room.domain;

import lombok.Data;

@Data
public class Company {
    private Integer id;

    private String name;

    private String code;

    private Long cost;

}