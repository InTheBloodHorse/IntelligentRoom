package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.File;
import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.OSSTypeEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.file.FileForm;
import cn.lsu.chicken.room.form.file.FileQueryForm;
import cn.lsu.chicken.room.service.FileService;
import cn.lsu.chicken.room.service.MeetingRoomService;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.HttpUtil;
import cn.lsu.chicken.room.utils.ResultVOUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    private UserService userService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private FileService fileService;

    @PostMapping("/headImage")
    public ResultVO<String> headImage(@RequestParam("id") Integer id,
                                      @RequestParam("file") MultipartFile file) {
        String url = HttpUtil.uploadFile(file, OSSTypeEnum.HEAD.getCode());
        User user = new User();
        user.setId(id);
        user.setAvatar(url);
        userService.uploadBySelective(user);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/meetingRoomCover")
    public ResultVO<String> meetingRoomCover(@RequestParam("meetingRoomId") Integer meetingRoomId,
                                             @RequestParam("file") MultipartFile file) {
        String url = HttpUtil.uploadFile(file, OSSTypeEnum.SYSTEM.getCode());
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setId(meetingRoomId);
        meetingRoom.setCover(url);
        meetingRoomService.uploadCover(meetingRoom);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/addMeetingRoomAuto")
    public ResultVO<String> addMeetingRoomAuto(@RequestParam("meetingRoomId") Integer meetingRoomId,
                                               @RequestParam("file") MultipartFile file) {

        String url = HttpUtil.uploadFile(file, OSSTypeEnum.SYSTEM.getCode());
        File entity = new File();
        entity.setUrl(url);
        entity.setMeetingRoomId(meetingRoomId);
        fileService.saveEntity(entity);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/delete")
    public ResultVO<Integer> deleteMeetingRoomAuto(HttpServletRequest httpServletRequest) {
        JsonObject params = HttpRequestUtil.getJson(httpServletRequest);
        Integer id = HttpRequestUtil.getIntegerByName(params, "fileId");
        File file = fileService.getEntityById(id);
        String url = file.getUrl();
        Integer column = fileService.deleteEntity(id);
        if (column == 1 && !StringUtils.isEmpty(url)) {
            HttpUtil.deleteFile(url);
        }
        return ResultVOUtil.success(column);
    }

    @PostMapping("/uploadFileToApply")
    public ResultVO<String> uploadFileToApply(@RequestParam("meetingApplyId") Integer meetingApplyId,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam("uploadUserId") Integer uploadUserId) {

        String url = HttpUtil.uploadFile(file, OSSTypeEnum.RESOURCES.getCode());
        File entity = new File();
        entity.setUrl(url);
        entity.setUploadUserId(uploadUserId);
        entity.setFilename(file.getOriginalFilename());
        entity.setMeetingApplyId(meetingApplyId);
        fileService.saveEntity(entity);
        return ResultVOUtil.success(url);
    }

    @PostMapping("/updateFileName")
    public ResultVO<Integer> updateFileName(@Valid @RequestBody FileForm fileForm,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("修改信息,参数不正确,fileForm={}", fileForm);
            throw new GlobalException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        File file = new File();
        file.setId(fileForm.getId());
        file.setFilename(fileForm.getFileName());
        Integer column = fileService.updateEntity(file);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/listEntity")
    public ResultVO<PageDTO<File>> listEntity(@Valid @RequestBody FileQueryForm fileQueryForm) {
        PageDTO<File> data = fileService.listEntityByQueryForm(fileQueryForm);
        return ResultVOUtil.success(data);
    }

}
