package cn.lsu.chicken.room.form.department;

import cn.lsu.chicken.room.domain.Department;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DepartmentUpdateForm {
    @NotNull(message = "请输入部门编号")
    private Integer id;

    @Size(max = 10, message = "部门名最大长度为10")
    private String name;

    private Integer companyId;

    public Department convert() {
        Department department = new Department();
        BeanUtils.copyProperties(this, department);
        return department;
    }
}
