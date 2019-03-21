package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.Camera;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.ImageInfo;
import cn.lsu.chicken.room.dto.UserFaceDTO;
import cn.lsu.chicken.room.enums.OSSTypeEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.face.Arcsoft;
import cn.lsu.chicken.room.face.config.FaceInitRunner;
import cn.lsu.chicken.room.face.utils.FaceUtil;
import cn.lsu.chicken.room.service.CameraService;
import cn.lsu.chicken.room.service.FaceService;
import cn.lsu.chicken.room.service.SignService;
import cn.lsu.chicken.room.service.UserService;
import cn.lsu.chicken.room.utils.*;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

import static cn.lsu.chicken.room.face.utils.FaceUtil.getFeatureByZipFile;

@RestController
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private FaceService faceService;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private SignService signService;

    @Autowired
    private Environment environment;

    @PostMapping("/addFaceFeature")
    public ResultVO<Integer> addFaceFeature(@RequestParam("file") MultipartFile file) {
        Map<String, Map<String, String>> data = getFeatureByZipFile(file);
        Integer column = faceService.updateFace(data);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/judgeAuth")
    public ResultVO<UserFaceDTO> judgeAuth(@RequestParam("file") MultipartFile file,
                                           @RequestParam("meeting_room_id") Integer meetingRommId,
                                           @RequestParam("meeting_apply_id") Integer meetingApplyId) {

        FaceEngine faceEngine = FaceInitRunner.faceEngine;
        File userFace = HttpUtil.multipartFile2File(file);
        ImageInfo imageInfo = Arcsoft.getRGBData(userFace);
        List<FaceInfo> faceInfoList = FaceUtil.getFaceList(faceEngine, imageInfo);
        //提取人脸特征
        FaceFeature faceFeature = FaceUtil.getFaceFeature(faceEngine, imageInfo, faceInfoList.get(0));
        List<UserFaceDTO> users = faceService.listUserFace();
        for (UserFaceDTO user : users) {
            FaceFeature faceFeature1 = new FaceFeature();
            String featureStr = user.getFace();
            String[] strArray = featureStr.substring(1, featureStr.length() - 1).split(",");
            byte[] faceByte = StringUtil.strArray2byetArray(strArray);
            faceFeature1.setFeatureData(faceByte);
            float a = FaceUtil.compareFeature(faceEngine, faceFeature, faceFeature1);
            float threshold = Float.valueOf(environment.getProperty("threshold"));
            if (a >= threshold) {
                Camera camera = new Camera();
                camera.setMeetingApplyId(meetingApplyId);
                camera.setMeetingRoomId(meetingRommId);
                String url = HttpUtil.uploadFile(userFace, OSSTypeEnum.FACE.getCode());
                camera.setUrl(url);
                camera.setUserId(user.getId());
                // 保存记录
                cameraService.saveEntity(camera);

                // 签到
                signService.signSuccess(meetingApplyId, user.getId());
                return ResultVOUtil.success(user);
            }
        }
        return ResultVOUtil.error(ResultEnum.FACE_NOT_MATCH);
    }

    @GetMapping("/listUserFace")
    public ResultVO<UserFaceDTO> listUserFace() {
        return ResultVOUtil.success(faceService.listUserFace());
    }
}
