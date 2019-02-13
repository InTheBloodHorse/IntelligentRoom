package cn.lsu.chicken.room.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MeetingApply {
    private Integer id;

    private Integer workerId;

    private String topic;

    private Integer roomId;

    private Integer attendance;

    private Date beginTime;

    private Date endTime;

    private Integer status;

    private Date applyTime;

    private Date applyUpdateTime;

    private Long price;

    private Integer flexible;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getApplyUpdateTime() {
        return applyUpdateTime;
    }

    public void setApplyUpdateTime(Date applyUpdateTime) {
        this.applyUpdateTime = applyUpdateTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getFlexible() {
        return flexible;
    }

    public void setFlexible(Integer flexible) {
        this.flexible = flexible;
    }
}