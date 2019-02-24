package cn.lsu.chicken.room.form.meetingroom;

import cn.lsu.chicken.room.domain.MeetingRoom;
import org.springframework.beans.BeanUtils;

public class BaseMeetingRoomForm {
    public MeetingRoom convert() {
        MeetingRoom meetingRoom = new MeetingRoom();
        BeanUtils.copyProperties(this, meetingRoom);
        return meetingRoom;
    }
}
