package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.domain.Company;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Integer id;
    private String name;

    private Company company;

}
