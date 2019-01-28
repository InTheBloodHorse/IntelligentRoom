package cn.lsu.chicken.room.entity;

import cn.lsu.chicken.room.enums.MeetingEnum;
import cn.lsu.chicken.room.utils.KeyUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class MeetingApply {
    @Id
    private String id = KeyUtil.genUniqueKey();

    //发起人编号
    private Integer workerId;

    //会议主题
    private String topic;

    //会议介绍
    private String intro;

    //会议文件列表
    private String documentList;

    //会议室编号
    private Integer roomId;

    //参加人数
    private Integer attendance;

    //开始时间
    private Date beginTime;

    //结束时间
    private Date endTime;

    //状态 1 安排 0未安排
    private Integer status = 0;

    //预约时间
    private Date applyTime;

    //预约更新时间
    private Date applyUpdateTime;

    //实际价格
    private BigDecimal price = new BigDecimal(0);
}
