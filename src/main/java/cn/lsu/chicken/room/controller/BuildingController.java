package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Building;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.building.BuildingForm;
import cn.lsu.chicken.room.form.building.BuildingQueryForm;
import cn.lsu.chicken.room.service.BuildingService;
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
@RequestMapping("/building")
@Slf4j
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/add")
    public ResultVO<Integer> add(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        String location = params.get("location").getAsString();
        Building building = new Building();
        building.setLocation(location);
        Integer id = buildingService.saveEntity(building);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        Integer column = buildingService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody BuildingForm buildingForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,buildingForm={}", buildingForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Building building = buildingForm.convert2Building();
        Integer column = buildingService.updateEntity(building);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/get")
    public ResultVO<Building> get(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        Building building = buildingService.getEntityById(id);
        return ResultVOUtil.success(building);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<Building>> listEntity(@Valid @RequestBody BuildingQueryForm buildingQueryForm) {

        PageDTO<Building> data = buildingService.listEntityByQueryForm(buildingQueryForm);
        return ResultVOUtil.success(data);
    }

}
