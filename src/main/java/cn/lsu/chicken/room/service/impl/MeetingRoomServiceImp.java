package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.convert.MeetingRoom2MeetingRoomDTO;
import cn.lsu.chicken.room.dao.MeetingRoomRepository;

import cn.lsu.chicken.room.dao.TagRepository;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.entity.MeetingRoom;
import cn.lsu.chicken.room.entity.Tag;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.MeetingRoomQueryForm;
import cn.lsu.chicken.room.service.MeetingRoomService;
import cn.lsu.chicken.room.utils.KeyUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class MeetingRoomServiceImp implements MeetingRoomService {
    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @Autowired
    private TagRepository tagRepository;

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
    public List<MeetingRoomDTO> findByManyConditions(MeetingRoomQueryForm meetingRoomQueryForm) {
        return null;
//        return meetingRoomRepository.findAll((root, cq, cb) -> {
//            //and 条件
//            List<Predicate> predicateListAnd = new ArrayList<>();
//            //楼层
//            if (meetingRoomQueryForm.getBuildingId() != null) {
//                predicateListAnd.add(cb.equal(root.get("buildingId"), meetingRoomQueryForm.getBuildingId()));
//            }
//            //容量
//            if (meetingRoomQueryForm.getMinVolume() != null) {
//                predicateListAnd.add(cb.greaterThanOrEqualTo(root.get("volume"), meetingRoomQueryForm.getMinVolume()));
//            }
//            if (meetingRoomQueryForm.getMaxVolume() != null) {
//                predicateListAnd.add(cb.lessThanOrEqualTo(root.get("volume"), meetingRoomQueryForm.getMaxVolume()));
//            }
//            //价格
//            if (meetingRoomQueryForm.getMinPrice() != null) {
//                predicateListAnd.add(cb.greaterThanOrEqualTo(root.get("price"), meetingRoomQueryForm.getMinPrice()));
//            }
//            if (meetingRoomQueryForm.getMaxPrice() != null) {
//                predicateListAnd.add(cb.lessThanOrEqualTo(root.get("price"), meetingRoomQueryForm.getMaxPrice()));
//            }
//            //是否使用
//            if (meetingRoomQueryForm.getIsUsing() != null) {
//                predicateListAnd.add(cb.equal(root.get("isUsing"), meetingRoomQueryForm.getIsUsing()));
//            }
//            //标签
//            if (meetingRoomQueryForm.getTags() != null) {
//                List<String> tags = Arrays.asList(meetingRoomQueryForm.getTags().split(","));
//                for (String tag : tags) {
//                    if (tag.length() != KeyUtil.preLen + KeyUtil.sufLen) continue;
//                    predicateListAnd.add(cb.like(root.get("tags").as(String.class), "%" + tag + "%"));
//                }
//            }
//            Predicate queryAnd = cb.and(predicateListAnd.toArray(new Predicate[predicateListAnd.size()]));
//            return cq.where(queryAnd).getRestriction();
//        });
    }
}
