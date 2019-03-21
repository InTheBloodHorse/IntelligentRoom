package cn.lsu.chicken.room.domain;

import lombok.Data;

@Data
public class Weight {
    private Integer id;

    private Integer companyId;

    private Integer updateTime;

    private Integer delayTime;

    private Integer cancelTime;

}