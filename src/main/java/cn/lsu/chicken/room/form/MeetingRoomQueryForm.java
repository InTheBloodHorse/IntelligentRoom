package cn.lsu.chicken.room.form;

import lombok.Data;

@Data
public class MeetingRoomQueryForm {
    private Integer buildingId;
    private Integer minVolume;
    private Integer maxVolume;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer isUsing = 0;
    private String tags;
}
