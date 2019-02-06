package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.AttendWorker;
import cn.lsu.chicken.room.entity.MeetingApply;
import cn.lsu.chicken.room.entity.User;

import java.util.List;

public interface AttendWorkerService {

    void addAttendWorker(String applyId, List<Integer> workerId);

    void deleteAttendWorker(List<AttendWorker> attendWorkers);


}
