package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.convert.MeetingRoom2MeetingRoomDTO;
import cn.lsu.chicken.room.dao.MeetingRoomRepository;

import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.dto.conditions.MeetingConditions;
import cn.lsu.chicken.room.entity.MeetingRoom;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.MeetingRoomQueryForm;
import cn.lsu.chicken.room.service.MeetingRoomService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
    @Autowired
    private MeetingRoomRepository meetingRoomRepository;


    @Override
    public void saveMeetingRoom(MeetingRoom meetingRoom) {
        meetingRoomRepository.save(meetingRoom);
    }


    @Override
    public void deleteMeetingRoom(Integer id) {
        try {
            meetingRoomRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.MEETING_ROOM_NOT_EXITS);
        }
    }

    @Override
    public MeetingRoomDTO findMeetRoomById(Integer id) {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(id).orElse(null);
        if (meetingRoom == null) {
            throw new GlobalException(ResultEnum.MEETING_ROOM_NOT_EXITS);
        }
        return MeetingRoom2MeetingRoomDTO.convert(meetingRoom);
    }

    @Override
    public PageDTO<MeetingRoomDTO> findByManyConditions(MeetingRoomQueryForm meetingRoomQueryForm, Pageable pageable) {
        Page<MeetingRoom> meetingRoomList = meetingRoomRepository.findAll(
                MeetingConditions.getMeetingSpecitication(meetingRoomQueryForm), pageable);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setData(MeetingRoom2MeetingRoomDTO.convert(meetingRoomList.getContent()));
        pageDTO.setPage(pageable.getPageNumber());
        pageDTO.setSize(pageable.getPageSize());
        pageDTO.setTotal(meetingRoomList.getTotalElements());
        pageDTO.setTotalPage(meetingRoomList.getTotalPages());
        return pageDTO;
    }

    @Override
    public List<MeetingRoomDTO> findByManyConditions(MeetingRoomQueryForm meetingRoomQueryForm) {
        Specification specification = MeetingConditions.getMeetingSpecitication(meetingRoomQueryForm);
        List<MeetingRoom> meetingRoomList = meetingRoomRepository.findAll(specification);
        return MeetingRoom2MeetingRoomDTO.convert(meetingRoomList);
    }

}
