package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.VO.ResultVO;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.ImageInfo;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.face.Arcsoft;
import cn.lsu.chicken.room.face.config.FaceInitRunner;
import cn.lsu.chicken.room.face.utils.FaceUtil;
import cn.lsu.chicken.room.service.FaceService;
import cn.lsu.chicken.room.utils.*;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Action;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static cn.lsu.chicken.room.face.utils.FaceUtil.getFeatureByZipFile;

@RestController
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private FaceService faceService;

    @Autowired
    private Environment environment;

    @PostMapping("/addFaceFeature")
    public ResultVO<Integer> addFaceFeature(@RequestParam("file") MultipartFile file) {
        Map<String, String> data = getFeatureByZipFile(file);
        Integer column = faceService.updateFace(data);
        return ResultVOUtil.success(column);
    }

    @PostMapping("/judgeAuth")
    public ResultVO<User> judgeAuth(@RequestParam("file") MultipartFile file) {
        FaceEngine faceEngine = FaceInitRunner.faceEngine;
        File userFace = HttpUtil.multipartFile2File(file);
        ImageInfo imageInfo = Arcsoft.getRGBData(userFace);
        List<FaceInfo> faceInfoList = FaceUtil.getFaceList(faceEngine, imageInfo);
        //提取人脸特征
        FaceFeature faceFeature = FaceUtil.getFaceFeature(faceEngine, imageInfo, faceInfoList.get(0));
        HttpUtil.deleteFile(userFace);
        List<User> users = faceService.listUserFace();
        for (User user : users) {
            FaceFeature faceFeature1 = new FaceFeature();
            String featureStr = user.getFace();
            String[] strArray = featureStr.substring(1, featureStr.length() - 1).split(",");
            byte[] faceByte = StringUtil.strArray2byetArray(strArray);
            faceFeature1.setFeatureData(faceByte);
            float a = FaceUtil.compareFeature(faceEngine, faceFeature, faceFeature1);
            float threshold = Float.valueOf(environment.getProperty("threshold"));
            System.out.println(a);
            if (a >= threshold) {
                return ResultVOUtil.success(user);
            }
        }
        System.out.println("---------------");
        return ResultVOUtil.error(ResultEnum.FACE_NOT_MATCH);
    }

    @PostMapping("/test")
    public void test(@RequestParam("file") MultipartFile file) {
        FaceEngine faceEngine = FaceInitRunner.faceEngine;
        File userFace = HttpUtil.multipartFile2File(file);
        ImageInfo imageInfo = Arcsoft.getRGBData(userFace);
        List<FaceInfo> faceInfoList = FaceUtil.getFaceList(faceEngine, imageInfo);
        //提取人脸特征
        FaceFeature faceFeature = FaceUtil.getFaceFeature(faceEngine, imageInfo, faceInfoList.get(0));
        System.out.println(FaceUtil.getFaceFeatureData(faceFeature));
    }
}
