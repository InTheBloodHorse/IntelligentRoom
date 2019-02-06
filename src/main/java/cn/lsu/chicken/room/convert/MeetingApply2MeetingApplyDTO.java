package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.dto.MeetingApplyDTO;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.entity.MeetingApply;
import cn.lsu.chicken.room.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MeetingApply2MeetingApplyDTO {

    @Autowired
    private UserService userService;

    @Autowired
    private static UserService service;

    @PostConstruct
    public void init() {
        service = userService;
    }

    public static MeetingApplyDTO convert(MeetingApply result) {
        MeetingApplyDTO meetingApplyDTO = new MeetingApplyDTO();
        BeanUtils.copyProperties(result, meetingApplyDTO);
        UserDTO worker = service.getUserById(result.getWorkerId());
        meetingApplyDTO.setUser(worker);
        return meetingApplyDTO;
    }
}
