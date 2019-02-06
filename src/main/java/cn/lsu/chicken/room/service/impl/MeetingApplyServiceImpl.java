package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.convert.MeetingApply2MeetingApplyDTO;
import cn.lsu.chicken.room.dao.MeetingApplyRepository;
import cn.lsu.chicken.room.dto.MeetingApplyDTO;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.entity.Company;
import cn.lsu.chicken.room.entity.MeetingApply;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.service.MeetingApplyService;
import cn.lsu.chicken.room.service.MeetingRoomService;
import cn.lsu.chicken.room.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeetingApplyServiceImpl implements MeetingApplyService {

    @Autowired
    private MeetingApplyRepository applyRepository;


    @Override
    public MeetingApplyDTO addApply(MeetingApply meetingApply) {
        judgeDate(meetingApply);
        MeetingApply result = applyRepository.save(meetingApply);
        return MeetingApply2MeetingApplyDTO.convert(result);
    }

    @Override
    public MeetingApplyDTO updateApply(MeetingApply meetingApply) {
        if (meetingApply.getId() == null || applyRepository.findById(meetingApply.getId()) == null) {
            throw new GlobalException(ResultEnum.APPLY_NOT_EXITS);
        }
        judgeDate(meetingApply);
        MeetingApply result = applyRepository.save(meetingApply);
        return MeetingApply2MeetingApplyDTO.convert(result);
    }

    @Override
    public void deleteApply(String id) {
        try {
            applyRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.APPLY_NOT_EXITS);
        }
    }

    @Override
    public List<MeetingApply> listMeetingApplyByUserId(Integer id) {
        return null;
    }

    private void judgeDate(MeetingApply meetingApply) {
        Date beginTime = meetingApply.getBeginTime();
        Date endTime = meetingApply.getEndTime();
        if (beginTime.before(new Date()) || endTime.before(beginTime)) {
            throw new GlobalException(ResultEnum.WRONG_DATE_STR);
        }
    }

}
