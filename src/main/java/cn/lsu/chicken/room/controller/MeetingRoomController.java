package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.meetingroom.MeetingRoomForm;
import cn.lsu.chicken.room.form.meetingroom.MeetingRoomQueryForm;
import cn.lsu.chicken.room.form.meetingroom.MeetingRoomUpdateForm;
import cn.lsu.chicken.room.service.MeetingRoomService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import cn.lsu.chicken.room.utils.StringUtil;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/meetingRoom")
@Slf4j
public class MeetingRoomController {
    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("/add")
    public ResultVO<Integer> add(@Valid @RequestBody MeetingRoomForm meetingRoomForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("添加会议室,参数不正确,meetingRoomForm={}", meetingRoomForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        MeetingRoom meetingRoom = meetingRoomForm.convert();
        Integer id = meetingRoomService.saveEntity(meetingRoom);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = meetingRoomService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody MeetingRoomUpdateForm meetingRoomUpdateForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,meetingRoomUpdateForm={}", meetingRoomUpdateForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        MeetingRoom meetingRoom = meetingRoomUpdateForm.convert();
        Integer column = meetingRoomService.updateEntity(meetingRoom);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/getEntity")
    public ResultVO<MeetingRoomDTO> getEntity(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        MeetingRoomDTO meetingRoomDTO = meetingRoomService.getEntityById(id);
        return ResultVOUtil.success(meetingRoomDTO);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<MeetingRoomDTO>> listEntity(@Valid @RequestBody MeetingRoomQueryForm meetingRoomQueryForm) {

        PageDTO<MeetingRoomDTO> data = meetingRoomService.listEntityByQueryForm(meetingRoomQueryForm);
        return ResultVOUtil.success(data);
    }

    @PostMapping("/updateTag")
    public ResultVO<Integer> updateTag(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        String tag = HttpRequestUtil.getStringByName(params, "tag");
        List<String> list = new ArrayList<>();
        if (!StringUtils.isEmpty(tag)) {
            list = Arrays.asList(tag.split(","));
        }
        List<Integer> tagId = StringUtil.stringList2IntegerList(list);
        Integer column = meetingRoomService.updateTag(id, tagId);
        return ResultVOUtil.success(column);
    }
}
