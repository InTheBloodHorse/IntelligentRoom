package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.domain.Building;
import cn.lsu.chicken.room.domain.Tag;
import lombok.Data;

import java.util.List;

@Data
public class MeetingRoomDTO {
    private Integer id;

    private String name;

    private Building building;

    private String address;

    private Byte volume;

    private Byte isUsing;

    private Short price;

    private String detail;

    private List<Tag> tag;
}
