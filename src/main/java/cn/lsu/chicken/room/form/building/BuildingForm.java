package cn.lsu.chicken.room.form.building;

import cn.lsu.chicken.room.domain.Building;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BuildingForm {

    @NotNull(message = "编号不能为空")
    private Integer id;

    @NotBlank(message = "楼幢不能为空")
    private String location;

    public Building convert2Building() {
        Building building = new Building();
        BeanUtils.copyProperties(this, building);
        return building;
    }
}
