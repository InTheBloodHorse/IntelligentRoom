package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.entity.Tag;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MeetingRoomDTO {

    private Integer id;

    //会议室名称
    private String name;

    //楼层编号
    private Integer buildingId;

    //会议室地址
    private String address;

    //可容纳容量
    private Integer volume;

    //是否在使用
    private Integer isUsing = 0;

    //价格
    private BigDecimal price = new BigDecimal(0);

    //标签
    private List<Tag> tags;

    //详情
    private String detail;
}
