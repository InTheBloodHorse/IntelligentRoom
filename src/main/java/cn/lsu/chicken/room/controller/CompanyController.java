package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Company;
import cn.lsu.chicken.room.dto.CompanyDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.company.CompanyForm;
import cn.lsu.chicken.room.form.company.CompanyQueryForm;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import cn.lsu.chicken.room.utils.StringUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public ResultVO<Integer> add(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        String name = params.get("name").getAsString();
        Company company = new Company();
        company.setName(name);
        Integer id = companyService.saveEntity(company);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        Integer column = companyService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody CompanyForm companyForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,companyForm={}", companyForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Company company = companyForm.convert();
        Integer column = companyService.updateEntity(company);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/get")
    public ResultVO<CompanyDTO> get(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        CompanyDTO companyDTO = companyService.getEntityById(id);
        return ResultVOUtil.success(companyDTO);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<CompanyDTO>> listEntity(@Valid @RequestBody CompanyQueryForm companyQueryForm) {

        PageDTO<CompanyDTO> data = companyService.listEntityByQueryForm(companyQueryForm);
        return ResultVOUtil.success(data);
    }

    @PostMapping("/updateHr")
    public ResultVO<Integer> updateHr(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        String hr = params.get("hr").getAsString();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        List<String> list = new ArrayList<>();
        if (!StringUtils.isEmpty(hr)) {
            list = Arrays.asList(hr.split(","));
        }
        List<Integer> hrId = StringUtil.stringList2IntegerList(list);
        Integer column = companyService.updateHr(id, hrId);
        return ResultVOUtil.success(column);
    }
}
