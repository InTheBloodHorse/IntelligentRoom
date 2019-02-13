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
        meetingRoomMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(MeetingRoom entity) {
        Integer id = entity.getId();
        Integer buildingId = entity.getBuildingId();
        String name = entity.getName();
        //假如当前标签修改了名称

        if (judgeExistByIdAndBuildingIdAndName(id, buildingId, name) == false) {
            //校验名称是否合法
            judgeExistByBuildingIdAndName(buildingId, name);
        }

        return meetingRoomMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return meetingRoomMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public MeetingRoomDTO getEntityById(Integer integer) {
        MeetingRoomDTO result = meetingRoomMapper.selectByPrimaryKey(integer);
        return result;

    }

    @Override
    public List<MeetingRoomDTO> listEntity() {
        return meetingRoomMapper.selectByExample(new MeetingRoomExample());
    }

    @Override
    public PageDTO<MeetingRoomDTO> listEntityByPage(PageHelper pageHelper) {
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample(pageHelper.getPage(), pageHelper.getSize());
        List<MeetingRoomDTO> data = meetingRoomMapper.selectByExample(meetingRoomExample);
        Integer total = meetingRoomMapper.countByExample(new MeetingRoomExample());
        PageDTO<MeetingRoomDTO> pageDTO = new PageDTO<>(pageHelper, total, data);
        return pageDTO;
    }

    private void judgeExistByBuildingIdAndName(Integer buildingId, String name) {
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andBuildingIdEqualTo(buildingId);
        criteria.andNameEqualTo(name);
        List<MeetingRoomDTO> result = meetingRoomMapper.selectByExample(meetingRoomExample);
        if (result.size() != 0) {
            throw new GlobalException(ResultEnum.MEETING_ROOM_IS_EXIST);
        }
    }

    private Boolean judgeExistByIdAndBuildingIdAndName(Integer id, Integer buildingId, String name) {
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        MeetingRoomExample meetingRoomExample = new MeetingRoomExample();
        MeetingRoomExample.Criteria criteria = meetingRoomExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andBuildingIdEqualTo(buildingId);
        criteria.andNameEqualTo(name);
        List<MeetingRoomDTO> result = meetingRoomMapper.selectByExample(meetingRoomExample);
        if (result.size() == 0) {
            return false;
        }
        return true;
    }
}
