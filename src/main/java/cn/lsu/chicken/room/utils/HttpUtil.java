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
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class HttpUtil {
    public static String ossUrl = "http://lsudjh.top:8082/oss/upload";

    public static String uploadFile(MultipartFile file, String type) {
        if (file == null) {
            throw new GlobalException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
        HttpPost httpPost = new HttpPost(ossUrl);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new GlobalException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        if (inputStream == null) {
            throw new GlobalException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        File uploadFile = multipartFile2File(file);
        multipartEntityBuilder.addBinaryBody("file", uploadFile);
        multipartEntityBuilder.addTextBody("type", type);
        multipartEntityBuilder.addTextBody("fileName", file.getOriginalFilename());
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        deleteFile(uploadFile);
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        if (statusCode == 200) {
            try {
                reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str = "";
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

    private static File multipartFile2File(MultipartFile multipartFile) {
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

    private static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
