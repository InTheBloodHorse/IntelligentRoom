package cn.lsu.chicken.room.service;


import cn.lsu.chicken.room.domain.User;

import java.util.List;
import java.util.Map;

public interface FaceService {
    Integer updateFace(Map<String, String> data);

    List<User> listUserFace();
}
