package cn.lsu.chicken.room.entity;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //预约单编号
    private String applyId;

    //员工编号
    private Integer workerId;

    //详细内容
    private String content;

    //申请时间
    private Date applyTime;

    //服务时间
    private Date serviceTime;


}
