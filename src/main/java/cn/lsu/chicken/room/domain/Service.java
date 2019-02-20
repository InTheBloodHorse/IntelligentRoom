package cn.lsu.chicken.room.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Service {
    private Integer id;

    private Integer applyId;

    private Integer workerId;

    private Date applyTime;

    private Date serviceTime;

    private String content;

}