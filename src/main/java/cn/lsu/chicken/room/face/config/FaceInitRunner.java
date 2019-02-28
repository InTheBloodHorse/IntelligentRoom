package cn.lsu.chicken.room.face.config;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FaceInitRunner implements ApplicationRunner {
    public static FaceEngine faceEngine;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        String appId = "EzU1ASsWJZVnAtwVGENdUfhgozqm32is8AtaW6rUoPyH";
//        String sdkKey = "Ehg4vSRg2cmohEEN4sPCZjCHGZdxhHBVSCfS3E86jKmd";
//
//        FaceEngine faceEngineCurrent = new FaceEngine();
//        faceEngineCurrent.active(appId, sdkKey);
//
//        EngineConfiguration engineConfiguration = EngineConfig.getEngineConfigration();
//        //初始化引擎
//        engineConfiguration.setDetectFaceMaxNum(5);
//        faceEngineCurrent.init(engineConfiguration);
//        faceEngine = faceEngineCurrent;
//        System.out.println("载入人脸引擎成功");
    }
}
