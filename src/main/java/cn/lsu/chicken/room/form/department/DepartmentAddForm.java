package cn.lsu.chicken.room.form.department;

import cn.lsu.chicken.room.domain.Department;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DepartmentAddForm {

    @NotBlank(message = "部门名称不能为空")
    @Size(max = 10, message = "部门名最大长度为10")
    private String name;

    @NotNull(message = "请选择公司")
    private Integer companyId;

    public Department convert() {
        Department department = new Department();
        BeanUtils.copyProperties(this, department);
        return department;
    }
}
