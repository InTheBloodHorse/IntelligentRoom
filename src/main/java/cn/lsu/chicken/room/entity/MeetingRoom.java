package cn.lsu.chicken.room.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String tags;

    //详情
    private String detail;

}
