package cn.lsu.chicken.room.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class MeetingApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer workerId;

    private String topic;

    private String intro;

    private String documentList;

    private Integer roomId;

    private Integer attendance;

    private Date beginTime;

    private Date endTime;

    private Integer status;

    private Date applyTime;

    private Date applyUpdateTime;
}
