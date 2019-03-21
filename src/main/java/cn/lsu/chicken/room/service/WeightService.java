package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.MeetingApply;
import cn.lsu.chicken.room.domain.Weight;
import cn.lsu.chicken.room.form.weight.WeightQueryForm;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WeightService extends BaseService<Weight, Weight, Integer, WeightQueryForm> {
    Integer DEFAULT_TOTAL = 100;

    Map<Integer, Integer> calculateWeight(List<Weight> weightList);

    // 加权随机算法
    Integer weightRandom(Map<Integer,Integer> weight);

    Integer applyInTwoDay(MeetingApply entity, Date current);

    void applyInWeek();

}
