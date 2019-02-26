package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.domain.MeetingApplyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface MeetingApplyMapper {
    int countByExample(MeetingApplyExample example);

    int deleteByExample(MeetingApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MeetingApply record);

    int insertSelective(MeetingApply record);


    List<MeetingApply> selectByExample(MeetingApplyExample example);

    MeetingApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MeetingApply record, @Param("example") MeetingApplyExample example);

    int updateByExample(@Param("record") MeetingApply record, @Param("example") MeetingApplyExample example);

    int updateByPrimaryKeySelective(MeetingApply record);


    int updateByPrimaryKey(MeetingApply record);

    int addAttenderWorker(@Param("applyId") Integer applyId, @Param("list") List<Integer> useId);

    int deleteAttenderWorker(@Param("applyId") Integer applyId, @Param("list") List<Integer> useId);

    int countByAttendUserIdExample(@Param("userId") Integer userId);

    List<MeetingApply> selectByAttendUserIdExample(@Param("userId") Integer userId, @Param("example")MeetingApplyExample example);
}