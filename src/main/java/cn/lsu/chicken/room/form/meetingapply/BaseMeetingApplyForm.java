package cn.lsu.chicken.room.form.meetingapply;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.utils.DateUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class BaseMeetingApplyForm {
    public MeetingApply convert(String beginTimeStr, String endTimeStr) {
        MeetingApply meetingApply = new MeetingApply();
        BeanUtils.copyProperties(this, meetingApply);
        Date beginTime = DateUtil.parse(beginTimeStr);
        meetingApply.setBeginTime(beginTime);
        Date endTime = DateUtil.parse(endTimeStr);
        meetingApply.setEndTime(endTime);
        return meetingApply;
    }
}
