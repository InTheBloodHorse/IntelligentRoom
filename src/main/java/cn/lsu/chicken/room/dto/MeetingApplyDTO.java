package cn.lsu.chicken.room.dto;

import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MeetingApplyDTO implements Serializable {
    private static final long serialVersionUID = 3136146901907754435L;
    private String id;

    //发起人编号
    private UserDTO user;

    //会议主题
    private String topic;

    //会议介绍
    private String intro;

    //会议文件列表
    private String documentList;

    //会议室编号
    private MeetingRoomDTO meetingRoom;

    //参加人数
    private Integer attendance;

    //开始时间
    private Date beginTime;

    //结束时间
    private Date endTime;

    //状态 1 安排 0未安排
    private Integer status = 0;

    //预约时间
    private Date applyTime;

    //预约更新时间
    private Date applyUpdateTime;

    //实际价格
    private BigDecimal price = new BigDecimal(0);

    //可否调剂 0不可调剂 1可调剂
    private Integer flexible = 0;

    public MeetingApplyDTO() {
    }

    public MeetingApplyDTO(String id, UserDTO user, String topic, String intro, String documentList,
                           MeetingRoomDTO meetingRoom, Integer attendance, Date beginTime,
                           Date endTime, Integer status, Date applyTime, Date applyUpdateTime,
                           BigDecimal price, Integer flexible) {
        this.id = id;
        this.user = user;
        this.topic = topic;
        this.intro = intro;
        this.documentList = documentList;
        this.meetingRoom = meetingRoom;
        this.attendance = attendance;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.status = status;
        this.applyTime = applyTime;
        this.applyUpdateTime = applyUpdateTime;
        this.price = price;
        this.flexible = flexible;
    }
}
