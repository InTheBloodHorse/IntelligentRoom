package cn.lsu.chicken.room.face.config;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FaceInitRunner implements ApplicationRunner {
    @Autowired
    private Environment environment;
    public static FaceEngine faceEngine;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String appId = environment.getProperty("appId");
        String sdkKey = environment.getProperty("sdkKey");

        FaceEngine faceEngineCurrent = new FaceEngine();
        faceEngineCurrent.active(appId, sdkKey);

        EngineConfiguration engineConfiguration = EngineConfig.getEngineConfigration();
        //初始化引擎
        engineConfiguration.setDetectFaceMaxNum(5);
        faceEngineCurrent.init(engineConfiguration);
        faceEngine = faceEngineCurrent;
        System.out.println("载入人脸引擎成功");
    }
}
