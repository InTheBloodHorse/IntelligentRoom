package cn.lsu.chicken.room.form.meetingapply;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Worker2ApplyForm {
    @NotNull(message = "请输入会议预约编号")
    private Integer Id;

    @NotBlank(message = "用户列表不能为空")
    private String userId;
}
