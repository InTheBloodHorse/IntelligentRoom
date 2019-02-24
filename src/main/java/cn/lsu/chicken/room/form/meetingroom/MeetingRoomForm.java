package cn.lsu.chicken.room.form.meetingroom;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MeetingRoomForm extends BaseMeetingRoomForm {

    @NotBlank(message = "会议室名称不能为空")
    @Size(max = 20, message = "会议室名称最大长度为20")
    private String name;

    @NotNull(message = "楼幢不能为空")
    private Integer buildingId;

    @NotBlank(message = "会议室地址不能为空")
    @Size(max = 20, message = "会议室地址最大长度为50")
    private String address;

    @NotNull(message = "请输入会议室容量")
    @Min(value = 1, message = "容量不能小于1")
    private Integer volume;

    private String detail;

}
