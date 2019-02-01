package cn.lsu.chicken.room.dto.conditions;

import cn.lsu.chicken.room.form.MeetingRoomQueryForm;
import cn.lsu.chicken.room.utils.KeyUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
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
                Integer buildingId = meetingRoomQueryForm.getBuildingId();
                if (buildingId != null) {
                    predicateListAnd.add(criteriaBuilder.equal(root.get("buildingId"), buildingId));
                }
                //容量
                Integer minVolume = meetingRoomQueryForm.getMinVolume();
                if (minVolume != null) {
                    predicateListAnd.add(criteriaBuilder.greaterThanOrEqualTo(root.get("volume"), minVolume));
                }
                Integer maxVolume = meetingRoomQueryForm.getMaxVolume();
                if (maxVolume != null) {
                    predicateListAnd.add(criteriaBuilder.lessThanOrEqualTo(root.get("volume"), maxVolume));
                }
                //价格
                Integer minPrice = meetingRoomQueryForm.getMinPrice();
                if (minPrice != null) {
                    predicateListAnd.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
                }
                Integer maxPrice = meetingRoomQueryForm.getMaxPrice();
                if (maxPrice != null) {
                    predicateListAnd.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
                }
                //是否使用
                Integer isUsing = meetingRoomQueryForm.getIsUsing();
                if (isUsing != null) {
                    predicateListAnd.add(criteriaBuilder.equal(root.get("isUsing"), isUsing));
                }
                //标签
                String tagStr = meetingRoomQueryForm.getTags();
                if (tagStr != null) {
                    List<String> tags = Arrays.asList(tagStr.split(","));
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
