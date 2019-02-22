package cn.lsu.chicken.room.domain;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class Tag {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
        if (StringUtils.isEmpty(this.name)) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
    }
}