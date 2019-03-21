package cn.lsu.chicken.room.face.utils;

import cn.lsu.chicken.room.dto.ImageInfo;
import cn.lsu.chicken.room.enums.OSSTypeEnum;
import cn.lsu.chicken.room.face.Arcsoft;
import cn.lsu.chicken.room.face.config.FaceInitRunner;
import cn.lsu.chicken.room.utils.FileUtil;
import cn.lsu.chicken.room.utils.HttpUtil;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.enums.ImageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class FaceUtil {

    public static List<FaceInfo> getFaceList(FaceEngine faceEngine, ImageInfo imageInfo) {
        List<FaceInfo> faceInfoList = new ArrayList<>();
        faceEngine.detectFaces(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        return faceInfoList;
    }

    public static FaceFeature getFaceFeature(FaceEngine faceEngine, ImageInfo imageInfo, FaceInfo faceInfo) {
        FaceFeature faceFeature = new FaceFeature();
        faceEngine.extractFaceFeature(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfo, faceFeature);
        return faceFeature;
    }

    public static String getFaceFeatureData(FaceFeature faceFeature) {
        return new String(Arrays.toString(faceFeature.getFeatureData()));
    }

    public static float compareFeature(FaceEngine faceEngine, FaceFeature f1, FaceFeature f2) {
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(f1.getFeatureData());

        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(f2.getFeatureData());

        FaceSimilar faceSimilar = new FaceSimilar();
        faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        return faceSimilar.getScore();
    }

    public static Map getFeatureByZipFile(MultipartFile multipartFile) {
        Map<String, Map<String, String>> data = new HashMap<>();
        FaceEngine faceEngine = FaceInitRunner.faceEngine;
        Map<String, File> fileList = new HashMap<>();
        fileList = FileUtil.readZipFile(multipartFile);
        List<File> files = new ArrayList<>();
        for (String key : fileList.keySet()) {
            File current = fileList.get(key);
            ImageInfo imageInfo = Arcsoft.getRGBData(current);
            List<FaceInfo> faceInfoList = getFaceList(faceEngine, imageInfo);
            FaceFeature feature = getFaceFeature(faceEngine, imageInfo, faceInfoList.get(0));
            files.add(current);
            String url = HttpUtil.uploadFile(current, OSSTypeEnum.FACE.getCode());
            data.put(key.split("\\.")[0], new HashMap() {
                {
                    put(getFaceFeatureData(feature), url);
                }
            });
        }
        File[] f = new File[files.size()];
        HttpUtil.deleteFile(files.toArray(f));
        return data;
    }
}
