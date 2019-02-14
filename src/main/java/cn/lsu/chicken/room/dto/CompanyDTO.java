package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class CompanyDTO {
    private Integer id;

    private String name;

    private String code;

    private Long cost;

    List<User> hr;
}
