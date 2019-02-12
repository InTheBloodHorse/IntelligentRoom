package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.domain.MeetingApplyExample;
import cn.lsu.chicken.room.domain.MeetingApplyWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface MeetingApplyMapper {
    int countByExample(MeetingApplyExample example);

    int deleteByExample(MeetingApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MeetingApplyWithBLOBs record);

    int insertSelective(MeetingApplyWithBLOBs record);

    List<MeetingApplyWithBLOBs> selectByExampleWithBLOBs(MeetingApplyExample example);

    List<MeetingApply> selectByExample(MeetingApplyExample example);

    MeetingApplyWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MeetingApplyWithBLOBs record, @Param("example") MeetingApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") MeetingApplyWithBLOBs record, @Param("example") MeetingApplyExample example);

    int updateByExample(@Param("record") MeetingApply record, @Param("example") MeetingApplyExample example);

    int updateByPrimaryKeySelective(MeetingApplyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MeetingApplyWithBLOBs record);

    int updateByPrimaryKey(MeetingApply record);
}