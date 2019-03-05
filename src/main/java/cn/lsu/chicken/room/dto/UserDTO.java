package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.domain.Department;
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

    private String avatar;

    private Integer role;

    private CompanyDTO company;

    private Department department;

}
