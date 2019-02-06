package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.AttendWorkerRepository;
import cn.lsu.chicken.room.entity.AttendWorker;
import cn.lsu.chicken.room.service.AttendWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendWorkerServiceImpl implements AttendWorkerService {

    @Autowired
    private AttendWorkerRepository attendWorkerRepository;

    @Override
    public void addAttendWorker(String applyId, List<Integer> workerId) {
        List<AttendWorker> attendWorkers = new ArrayList<>();
        for (Integer id : workerId) {
            AttendWorker attendWorker = new AttendWorker();
            attendWorker.setApplyId(applyId);
            attendWorker.setWorkerId(id);
            attendWorkers.add(attendWorker);
        }
        attendWorkerRepository.saveAll(attendWorkers);
    }

    @Override
    public void deleteAttendWorker(List<AttendWorker> attendWorkers) {
        attendWorkerRepository.deleteAll(attendWorkers);
    }
}
