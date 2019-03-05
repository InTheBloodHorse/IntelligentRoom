package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.Department;
import cn.lsu.chicken.room.dto.DepartmentDTO;
import cn.lsu.chicken.room.form.department.DepartmentQueryForm;

public interface DepartmentService extends BaseService<DepartmentDTO, Department, Integer, DepartmentQueryForm> {
}
