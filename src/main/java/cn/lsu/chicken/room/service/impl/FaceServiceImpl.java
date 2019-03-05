package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.UserMapper;
import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FaceServiceImpl implements FaceService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer updateFace(Map<String, String> data) {
        List<User> users = new ArrayList<>();
        for(String key:data.keySet()){
            String value = data.get(key);
            User user = new User();
            user.setId(Integer.valueOf(key));
            user.setFace(value);
            users.add(user);
        }
        return userMapper.updateFaceByList(users);
    }

    @Override
    public List<User> listUserFace() {
        List<User> users = userMapper.listUserFace();
        return users;
    }
}
