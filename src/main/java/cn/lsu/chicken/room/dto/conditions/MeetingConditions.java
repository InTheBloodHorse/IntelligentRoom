package cn.lsu.chicken.room.dto.conditions;

import cn.lsu.chicken.room.form.MeetingRoomQueryForm;
import cn.lsu.chicken.room.utils.KeyUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingConditions {

    public static Specification getMeetingSpecitication(MeetingRoomQueryForm meetingRoomQueryForm) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //and 条件
                List<Predicate> predicateListAnd = new ArrayList<>();
                //楼层
                if (meetingRoomQueryForm.getBuildingId() != null) {
                    predicateListAnd.add(criteriaBuilder.equal(root.get("buildingId"), meetingRoomQueryForm.getBuildingId()));
                }
                //容量
                if (meetingRoomQueryForm.getMinVolume() != null) {
                    predicateListAnd.add(criteriaBuilder.greaterThanOrEqualTo(root.get("volume"), meetingRoomQueryForm.getMinVolume()));
                }
                if (meetingRoomQueryForm.getMaxVolume() != null) {
                    predicateListAnd.add(criteriaBuilder.lessThanOrEqualTo(root.get("volume"), meetingRoomQueryForm.getMaxVolume()));
                }
                //价格
                if (meetingRoomQueryForm.getMinPrice() != null) {
                    predicateListAnd.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), meetingRoomQueryForm.getMinPrice()));
                }
                if (meetingRoomQueryForm.getMaxPrice() != null) {
                    predicateListAnd.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), meetingRoomQueryForm.getMaxPrice()));
                }
                //是否使用
                if (meetingRoomQueryForm.getIsUsing() != null) {
                    predicateListAnd.add(criteriaBuilder.equal(root.get("isUsing"), meetingRoomQueryForm.getIsUsing()));
                }
                //标签
                if (meetingRoomQueryForm.getTags() != null) {
                    List<String> tags = Arrays.asList(meetingRoomQueryForm.getTags().split(","));
                    for (String tag : tags) {
                        if (tag.length() != KeyUtil.preLen + KeyUtil.sufLen) continue;
                        predicateListAnd.add(criteriaBuilder.like(root.get("tags").as(String.class), "%" + tag + "%"));
                    }
                }
                Predicate queryAnd = criteriaBuilder.and(predicateListAnd.toArray(new Predicate[predicateListAnd.size()]));
                return criteriaQuery.where(queryAnd).getRestriction();
            }
        };
        return specification;
    }
}
