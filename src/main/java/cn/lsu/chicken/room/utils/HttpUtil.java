package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpUtil {
    public static String ossUrl = "http://lsudjh.top:8082/oss/";
//    public static String ossUrl = "http://127.0.0.1:8088/oss/upload";


    public static String uploadFile(MultipartFile file, String type) {
        if (file == null) {
            throw new GlobalException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        File uploadFile = multipartFile2File(file);
        Map<String, Object> params = new HashMap<>();
        params.put("file", uploadFile);
        params.put("type", type);
        String response = makeRequest(ossUrl + "upload", params);
        deleteFile(uploadFile);
        return response;
    }

    public static String deleteFile(String url) {
        Map<String, Object> params = new HashMap<>();
        params.put("url", url);
        return makeRequest(ossUrl + "delete", params);
    }

    public static File multipartFile2File(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        File file = null;
        try {
            file = File.createTempFile(UUID.randomUUID().toString(), prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String makeRequest(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for (String key : params.keySet()) {
            if ("file".equals(key)) {
                File uploadFile = (File) params.get(key);
                multipartEntityBuilder.addBinaryBody("file", uploadFile);
            } else {
                multipartEntityBuilder.addTextBody(key, (String) params.get(key));
            }
        }
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        if (statusCode == 200) {
            try {
                reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str;
            try {
                while (!StringUtils.isEmpty(str = reader.readLine())) {
                    buffer.append(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new GlobalException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
