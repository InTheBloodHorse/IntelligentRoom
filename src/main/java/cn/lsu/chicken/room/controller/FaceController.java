package cn.lsu.chicken.room.controller;

import cn.lsu.chicken.room.dto.ImageInfo;
import cn.lsu.chicken.room.face.Arcsoft;
import cn.lsu.chicken.room.face.config.EngineConfig;
import cn.lsu.chicken.room.face.config.FaceInitRunner;
import cn.lsu.chicken.room.face.utils.FaceUtil;
import cn.lsu.chicken.room.utils.HttpRequestUtil;
import cn.lsu.chicken.room.utils.HttpUtil;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/face")
public class FaceController {


    @PostMapping("/judgeAuth")
    public void judgeAuth(@RequestParam("file") MultipartFile file) {
        FaceEngine faceEngine = FaceInitRunner.faceEngine;
        ImageInfo imageInfo = Arcsoft.getRGBData(new File("C:\\Users\\InTheBloodHorse\\Desktop\\7.jpg"));
        List<FaceInfo> faceInfoList = FaceUtil.getFaceList(faceEngine, imageInfo);
        //提取人脸特征
        FaceFeature faceFeature = FaceUtil.getFaceFeature(faceEngine, imageInfo, faceInfoList.get(0));

        ImageInfo imageInfo1 = Arcsoft.getRGBData(HttpUtil.multipartFile2File(file));
        List<FaceInfo> faceInfoList1 = FaceUtil.getFaceList(faceEngine, imageInfo1);
        //提取人脸特征
        FaceFeature faceFeature1 = FaceUtil.getFaceFeature(faceEngine, imageInfo1, faceInfoList1.get(0));
        System.out.println(FaceUtil.getFaceFeatureData(faceFeature1));
        float a = FaceUtil.compareFeature(faceEngine, faceFeature, faceFeature1);
        System.out.printf("%f\n", a);
    }
}
