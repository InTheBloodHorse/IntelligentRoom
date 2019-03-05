package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Equipment;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.equipment.EquipmentQueryForm;
import cn.lsu.chicken.room.form.equipment.EquipmentUpdateForm;
import cn.lsu.chicken.room.service.EquipmentService;
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
@RequestMapping("/equipment")
@Slf4j
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/add")
    public ResultVO<Integer> add(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        String name = HttpRequestUtil.getStringByName(params, "name");
        Equipment equipment = new Equipment();
        equipment.setName(name);
        Integer id = equipmentService.saveEntity(equipment);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = equipmentService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody EquipmentUpdateForm equipmentUpdateForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,equipmentUpdateForm={}", equipmentUpdateForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Equipment equipment = equipmentUpdateForm.convert();
        Integer column = equipmentService.updateEntity(equipment);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/get")
    public ResultVO<Equipment> get(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Equipment tag = equipmentService.getEntityById(id);
        return ResultVOUtil.success(tag);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<Equipment>> listEntity(@Valid @RequestBody EquipmentQueryForm equipmentQueryForm) {

        PageDTO<Equipment> data = equipmentService.listEntityByQueryForm(equipmentQueryForm);
        return ResultVOUtil.success(data);
    }

}
