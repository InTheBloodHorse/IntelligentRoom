package cn.lsu.chicken.room.form.meetingroom;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MeetingRoomUpdateForm extends BaseMeetingRoomForm {

    @NotNull(message = "会议室编号不能为空")
    private Integer id;

    @NotBlank(message = "会议室名称不能为空")
    @Size(max = 20, message = "会议室名称最大长度为20")
    private String name;

    @NotNull(message = "楼幢不能为空")
    private Integer buildingId;

    @Size(max = 20, message = "会议室地址最大长度为50")
    private String address;

    @Min(value = 1, message = "容量不能小于1")
    private Integer volume;

    private String detail;

}
