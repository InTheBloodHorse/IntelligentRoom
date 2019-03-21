package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.domain.UserExample;

import java.util.List;

import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.dto.UserFaceDTO;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<UserDTO> selectByExample(UserExample example);

    UserDTO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int countByApplyIdExample(@Param("applyId") Integer applyId);

    List<User> selectByApplyIdExample(@Param("applyId") Integer applyId, @Param("example") UserExample example);

    Integer updateFaceByList(@Param("user") List<User> user);

    List<UserFaceDTO> listUserFace();

}