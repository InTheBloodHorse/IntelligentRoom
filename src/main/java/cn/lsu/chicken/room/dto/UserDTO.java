package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.entity.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    private String name;

    private String phone;

    private String email;

    @JsonIgnore
    private String password;

    //头像地址
    private String avatar;

    private Integer role;

    private Company company;
}
