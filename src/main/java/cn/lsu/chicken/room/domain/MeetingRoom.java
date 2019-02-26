package cn.lsu.chicken.room.domain;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class MeetingRoom {
    private Integer id;

    private String name;

    private Integer buildingId;

    private String address;

    private Integer volume;

    private String cover;

    private Integer isUsing;

    private BigDecimal price;

    private String detail;


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }


    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}