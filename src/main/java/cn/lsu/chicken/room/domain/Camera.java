package cn.lsu.chicken.room.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Camera {
    private Integer id;

    private Integer meetingRoomId;

    private Integer meetingApplyId;

    private Integer userId;

    private String url;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public Integer getMeetingApplyId() {
        return meetingApplyId;
    }

    public void setMeetingApplyId(Integer meetingApplyId) {
        this.meetingApplyId = meetingApplyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}