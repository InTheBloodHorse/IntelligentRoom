package cn.lsu.chicken.room.service;


import cn.lsu.chicken.room.dto.UserFaceDTO;

import java.util.List;
import java.util.Map;

public interface FaceService {
    Integer updateFace(Map<String, Map<String,String>> data);

    List<UserFaceDTO> listUserFace();
}
