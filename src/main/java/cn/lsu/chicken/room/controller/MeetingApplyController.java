package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.BaseQueryForm;
import cn.lsu.chicken.room.form.meetingapply.*;
import cn.lsu.chicken.room.service.MeetingApplyService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import cn.lsu.chicken.room.utils.StringUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/meetingApply")
@Slf4j
public class MeetingApplyController {
    @Autowired
    private MeetingApplyService meetingApplyService;

    @PostMapping("/apply")
    public ResultVO<Integer> add(@Valid @RequestBody MeetingApplyForm meetingApplyForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("添加会议室,参数不正确,meetingApplyForm={}", meetingApplyForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        MeetingApply meetingApply = meetingApplyForm.convert();
        Integer id = meetingApplyService.saveEntity(meetingApply);
        return ResultVOUtil.success(id);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> delete(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        Integer column = meetingApplyService.deleteEntity(id);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/update")
    public ResultVO<Integer> update(@Valid @RequestBody MeetingApplyUpdateForm meetingApplyUpdateForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,meetingApplyUpdateForm={}", meetingApplyUpdateForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        MeetingApply meetingApply = meetingApplyUpdateForm.convert();
        Integer column = meetingApplyService.updateEntity(meetingApply);
        return ResultVOUtil.success(column);
    }


    @PostMapping("/getEntity")
    public ResultVO<MeetingApply> getEntity(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "id");
        MeetingApply meetingRoomDTO = meetingApplyService.getEntityById(id);
        return ResultVOUtil.success(meetingRoomDTO);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<MeetingApply>> listEntity(@Valid @RequestBody MeetingApplyQueryForm meetingApplyQueryForm) {

        PageDTO<MeetingApply> data = meetingApplyService.listEntityByQueryForm(meetingApplyQueryForm);
        return ResultVOUtil.success(data);
    }

    @PostMapping("/addAttenderWorker")
    public ResultVO<Integer> addAttenderWorker(@Valid @RequestBody Worker2ApplyForm worker2ApplyForm,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("添加用户到会议,参数不正确,worker2ApplyForm={}", worker2ApplyForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Integer applyId = worker2ApplyForm.getId();
        List<Integer> userList = StringUtil.string2IntegerList(worker2ApplyForm.getUserId());
        Integer column = meetingApplyService.addAttenderWorker(applyId, userList);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/deleteAttenderWorker")
    public ResultVO<Integer> deleteAttenderWorker(@Valid @RequestBody Worker2ApplyForm worker2ApplyForm,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("在会议中移除用户,参数不正确,worker2ApplyForm={}", worker2ApplyForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Integer applyId = worker2ApplyForm.getId();
        List<Integer> userList = StringUtil.string2IntegerList(worker2ApplyForm.getUserId());
        Integer column = meetingApplyService.deleteAttenderWorker(applyId, userList);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/listByAttendWorker")
    public ResultVO<PageDTO<MeetingApply>> listByAttendWorker(@Valid @RequestBody AttenderQueryForm attenderQueryForm,
                                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("查询用户参与的会议,参数不正确,attenderQueryForm={}", attenderQueryForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        PageDTO<MeetingApply> data = meetingApplyService.listMeetingApplyByUserId(attenderQueryForm);
        return ResultVOUtil.success(data);
    }

}
