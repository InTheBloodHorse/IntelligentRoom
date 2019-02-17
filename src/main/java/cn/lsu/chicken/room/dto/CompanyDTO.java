package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.domain.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CompanyDTO {
    private Integer id;

    private String name;

    private String code;

    private BigDecimal cost;

    List<User> hr;
}
