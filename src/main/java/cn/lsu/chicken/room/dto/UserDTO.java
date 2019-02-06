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

    public UserDTO() {
    }

    public UserDTO(Object[][] o) {
        for(int i=0;i<o[0].length;i++){
            System.out.println(o[0][i]);
        }
        this.id = (int)o[0][0];
    }
}
