package cn.lsu.chicken.room.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String password;

    private String avatar;

    private Integer role;

    private Integer companyId;


}