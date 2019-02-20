package cn.lsu.chicken.room.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MeetingApply {
    private Integer id;

    private Integer workerId;

    private String topic;

    private String intro;

    private String documentList;

    private Integer roomId;

    private Integer attendance;

    private Date beginTime;

    private Date endTime;

    private Integer status;

    private Date applyTime;

    private Date applyUpdateTime;

    private Long price;

    private Integer flexible;

}