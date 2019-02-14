package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.domain.Building;
import cn.lsu.chicken.room.domain.Tag;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MeetingRoomDTO {
    private Integer id;

    private String name;

    private Building building;

    private String address;

    private Integer volume;

    private Integer isUsing;

    private BigDecimal price;

    private String detail;

    private List<Tag> tag;
}
