package cn.lsu.chicken.room.domain;

import lombok.Data;

@Data
public class File {
    private Integer id;

    private String filename;

    private String url;

    private Integer uploadUserId;

    private Integer meetingRoomId;

    private Integer meetingApplyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public Integer getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

}