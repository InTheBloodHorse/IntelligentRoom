package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.MeetingRoomRepository;
import cn.lsu.chicken.room.dao.TagRepository;
import cn.lsu.chicken.room.entity.MeetingRoom;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.MeetingRoomQueryForm;
import cn.lsu.chicken.room.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class MeetingRoomServiceImp implements MeetingRoomService {
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
            throw new GlobalException(ResultEnum.MEETINGROOM_NOT_EIXT);
        }
    }

    @Override
    public List<MeetingRoom> findByManyConditions(MeetingRoomQueryForm meetingRoomQueryForm) {
        List<MeetingRoom> meetingRoomList = new ArrayList<>();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateListAnd = new ArrayList<>();
                List<Predicate> predicateListOr = new ArrayList<>();
                if (meetingRoomQueryForm.getBuildingId() != null) {
                    predicateListAnd.add(criteriaBuilder.equal(root.get("buildingId"), meetingRoomQueryForm.getBuildingId()));
                }
                if (meetingRoomQueryForm.getMinVolume() != null) {
                    predicateListOr.add(criteriaBuilder.greaterThanOrEqualTo(root.get("volume"), meetingRoomQueryForm.getMinVolume()));
                }
                if (meetingRoomQueryForm.getMaxVolume() != null) {
                    predicateListOr.add(criteriaBuilder.lessThanOrEqualTo(root.get("volume"), meetingRoomQueryForm.getMaxVolume()));
                }
                if (meetingRoomQueryForm.getIsUsing() != null) {
                    predicateListAnd.add(criteriaBuilder.equal(root.get("isUsing"), meetingRoomQueryForm.getIsUsing()));
                }
                Predicate queryAnd = criteriaBuilder.and(predicateListAnd.toArray(new Predicate[predicateListAnd.size()]));
                Predicate queryOr = criteriaBuilder.or(predicateListOr.toArray(new Predicate[predicateListOr.size()]));
                return criteriaQuery.where(queryAnd, queryOr).getRestriction();
            }
        };
        meetingRoomList = meetingRoomRepository.findAll(specification);
        return meetingRoomList;
    }
}
