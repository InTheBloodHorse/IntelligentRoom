package cn.lsu.chicken.room.domain;

import java.util.Date;

public class MeetingApply {
    private String id;

    private Integer workerId;

    private String topic;

    private Integer roomId;

    private Integer attendance;

    private Date beginTime;

    private Date endTime;

    private Byte status;

    private Date applyTime;

    private Date applyUpdateTime;

    private Long price;

    private Byte flexible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    public Byte getFlexible() {
        return flexible;
    }

    public void setFlexible(Byte flexible) {
        this.flexible = flexible;
    }
}