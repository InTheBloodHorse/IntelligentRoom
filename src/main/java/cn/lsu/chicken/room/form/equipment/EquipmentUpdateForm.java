package cn.lsu.chicken.room.form.equipment;

import cn.lsu.chicken.room.domain.Equipment;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EquipmentUpdateForm {
    @NotNull(message = "设备编号不能为空")
    private Integer id;

    @NotBlank(message = "设备名不能为空")
    private String name;

    public Equipment convert() {
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(this, equipment);
        return equipment;
    }
}
