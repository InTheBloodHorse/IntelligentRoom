package cn.lsu.chicken.room.entity;

import cn.lsu.chicken.room.enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    private String email;

    @JsonIgnore
    private String password;

    //头像地址
    private String avatar;

    private Integer role = UserRoleEnum.ORDINARY_USER.getCode();

    private Integer companyId;

}
