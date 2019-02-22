package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.convert.tag.TagForm2Tag;
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

    @PostMapping("/addTag")
    public ResultVO<Integer> addTag(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        String name = params.get("name").getAsString();
        Tag tag = new Tag();
        tag.setName(name);
        Integer id = tagService.saveEntity(tag);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/deleteTag")
    public ResultVO deleteTag(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        tagService.deleteEntity(id);
        return ResultVOUtil.success();
    }

    @PostMapping("/updateTag")
    public ResultVO<Integer> updateTag(@Valid @RequestBody TagForm tagForm,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,tagForm={}", tagForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Tag tag = TagForm2Tag.convert(tagForm);
        Integer column = tagService.updateEntity(tag);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/getTag")
    public ResultVO<Tag> getTag(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = params.get("id").getAsInt();
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        Tag tag = tagService.getEntityById(id);
        return ResultVOUtil.success(tag);
    }

    @PostMapping("/listTag")
    public ResultVO<PageDTO<Tag>> listUser(@Valid @RequestBody TagQueryForm tagQueryForm) {

        PageDTO<Tag> data = tagService.listEntityByQueryForm(tagQueryForm);
        return ResultVOUtil.success(data);
    }
}
