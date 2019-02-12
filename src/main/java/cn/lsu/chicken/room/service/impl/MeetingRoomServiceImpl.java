package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.MeetingRoomMapper;
import cn.lsu.chicken.room.domain.MeetingRoom;
import cn.lsu.chicken.room.domain.MeetingRoomExample;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    @Override
    public Integer saveEntity(MeetingRoom entity) {
        judgeExistByBuildingIdAndName(entity.getBuildingId(), entity.getName());
        meetingRoomMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(MeetingRoom entity) {
        return null;
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return meetingRoomMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public MeetingRoom getEntityById(Integer integer) {
        List<MeetingRoomDTO> result = meetingRoomMapper.selectByPrimaryKey(integer);
        System.out.println(result);
        return null;

    }

    @Override
    public List<MeetingRoom> listEntity() {
        return null;
    }

    @Override
    public PageDTO<MeetingRoom> listEntityByPage(PageHelper pageHelper) {
        return null;
    }

    private void judgeExistByBuildingIdAndName(Integer buildingId, String name) {
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andBuildingIdEqualTo(buildingId);
        criteria.andNameEqualTo(name);
        List<MeetingRoom> result = meetingRoomMapper.selectByExample(meetingRoomExample);
        if (result.size() != 0) {
            throw new GlobalException(ResultEnum.MEETING_ROOM_IS_EXIST);
        }
    }
}
