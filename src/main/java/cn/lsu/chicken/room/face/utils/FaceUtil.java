package cn.lsu.chicken.room.face.utils;

import cn.lsu.chicken.room.entity.ImageInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.enums.ImageFormat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
