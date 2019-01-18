package cn.lsu.chicken.room.face.config;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FunctionConfiguration;

public class EngineConfig {
    public static EngineConfiguration getEngineConfigration() {
        return EngineConfiguration.builder().functionConfiguration(
                FunctionConfiguration.builder()
                        .supportAge(true)
                        .supportFace3dAngle(true)
                        .supportFaceDetect(true)
                        .supportFaceRecognition(true)
                        .supportGender(true)
                        .build()).build();
    }
}
