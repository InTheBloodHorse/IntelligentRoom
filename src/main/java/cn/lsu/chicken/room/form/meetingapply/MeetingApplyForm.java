package cn.lsu.chicken.room.form.meetingapply;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.utils.DateUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MeetingApplyForm extends BaseMeetingApplyForm {


    @NotNull(message = "员工编号不能为空")
    private Integer workerId;

    @NotBlank(message = "会议标题不能为空")
    private String topic;

    private String intro;

    private Integer roomId;

    @NotNull(message = "请输入会议人数")
    private Integer attendance;

    @NotBlank(message = "请选择开始时间")
    private String beginTime;

    @NotBlank(message = "请选择结束时间")
    private String endTime;

    // 默认可调剂
    @Min(value = 0, message = "请选择可否调剂")
    @Max(value = 1, message = "请选择可否调剂")
    private Integer flexible = 1;


    public MeetingApply convert() {
        return super.convert(this.getBeginTime(), this.getEndTime());
    }
}
