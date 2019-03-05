package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Department;
import cn.lsu.chicken.room.dto.DepartmentDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.department.DepartmentAddForm;
import cn.lsu.chicken.room.form.department.DepartmentQueryForm;
import cn.lsu.chicken.room.form.department.DepartmentUpdateForm;
import cn.lsu.chicken.room.service.DepartmentService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/add")
    public ResultVO<Integer> add(@Valid @RequestBody DepartmentAddForm departmentAddForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("添加部门,参数不正确,departmentAddForm={}", departmentAddForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Department department = departmentAddForm.convert();
        Integer id = departmentService.saveEntity(department);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = departmentService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody DepartmentUpdateForm departmentUpdateForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,departmentUpdateForm={}", departmentUpdateForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Department department = departmentUpdateForm.convert();
        Integer column = departmentService.updateEntity(department);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/get")
    public ResultVO<DepartmentDTO> get(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        DepartmentDTO departmentDTO = departmentService.getEntityById(id);
        return ResultVOUtil.success(departmentDTO);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<DepartmentDTO>> listEntity(@Valid @RequestBody DepartmentQueryForm departmentQueryForm) {

        PageDTO<DepartmentDTO> data = departmentService.listEntityByQueryForm(departmentQueryForm);
        return ResultVOUtil.success(data);
    }
}
