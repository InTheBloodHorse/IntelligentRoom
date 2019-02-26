package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestUtil {
    public static JsonObject getJson(HttpServletRequest request) {
        JsonObject jsonObject = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getStringByName(JsonObject jsonObject, String name) {
        JsonElement jsonElement = jsonObject.get(name);
        if (jsonElement == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        String data = jsonElement.getAsString().trim();
        if (StringUtils.isEmpty(data)) {
            throw new GlobalException(ResultEnum.STR_CAN_NOT_NULL);
        }
        return data;
    }

    public static Integer getIntegerByName(JsonObject jsonObject, String name) {
        JsonElement jsonElement = jsonObject.get(name);
        if (jsonElement == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        Integer data = jsonElement.getAsInt();
        if (StringUtils.isEmpty(data)) {
            throw new GlobalException(ResultEnum.ID_NEED);
        }
        return data;
    }
}
