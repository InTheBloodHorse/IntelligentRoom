package cn.lsu.chicken.room.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    @JsonIgnore
    private String password;

    private String avatar;

    private Integer role;

    private Integer companyId;

    private Integer departmentId;

    @JsonIgnore
    private String face;


}