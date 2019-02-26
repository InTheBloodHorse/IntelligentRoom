package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Service;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.service.AddServiceForm;
import cn.lsu.chicken.room.form.service.ServiceQueryForm;
import cn.lsu.chicken.room.service.ServiceService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/service")
@Slf4j
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/add")
    public ResultVO<Integer> add(@Valid @RequestBody AddServiceForm addServiceForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,addServiceForm={}", addServiceForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Service service = addServiceForm.convert();
        Integer id = serviceService.saveEntity(service);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = serviceService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        String content = HttpRequestUtil.getStringByName(params, "content");
        Service service = new Service();
        service.setId(id);
        service.setContent(content);
        Integer column = serviceService.updateEntity(service);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/get")
    public ResultVO<Service> get(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Service service = serviceService.getEntityById(id);
        return ResultVOUtil.success(service);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<Service>> listEntity(@Valid @RequestBody ServiceQueryForm serviceQueryForm) {

        PageDTO<Service> data = serviceService.listEntityByQueryForm(serviceQueryForm);
        return ResultVOUtil.success(data);
    }

    @PostMapping("/done")
    public ResultVO<Integer> done(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = serviceService.serviceDone(id);
        return ResultVOUtil.success(column);
    }
}
