package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Tag;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.tag.TagForm;
import cn.lsu.chicken.room.form.tag.TagQueryForm;
import cn.lsu.chicken.room.service.TagService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    public ResultVO<Integer> add(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        String name = HttpRequestUtil.getStringByName(params, "name");
        Tag tag = new Tag();
        tag.setName(name);
        Integer id = tagService.saveEntity(tag);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = tagService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody TagForm tagForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,tagForm={}", tagForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Tag tag = tagForm.convert();
        Integer column = tagService.updateEntity(tag);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/get")
    public ResultVO<Tag> get(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Tag tag = tagService.getEntityById(id);
        return ResultVOUtil.success(tag);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<Tag>> listEntity(@Valid @RequestBody TagQueryForm tagQueryForm) {

        PageDTO<Tag> data = tagService.listEntityByQueryForm(tagQueryForm);
        return ResultVOUtil.success(data);
    }
}
