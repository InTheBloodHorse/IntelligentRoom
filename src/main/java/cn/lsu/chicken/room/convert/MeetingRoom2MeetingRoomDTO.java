package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.dao.TagRepository;
import cn.lsu.chicken.room.dto.MeetingRoomDTO;
import cn.lsu.chicken.room.entity.MeetingRoom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MeetingRoom2MeetingRoomDTO {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private static TagRepository tagRepositoryStatic;

    @PostConstruct
    public void init() {
        tagRepositoryStatic = tagRepository;
    }

    public static MeetingRoomDTO convert(MeetingRoom meetingRoom) {
        MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO();
        BeanUtils.copyProperties(meetingRoom, meetingRoomDTO);
        List<String> tags = Arrays.asList(meetingRoom.getTags().split(","));
        meetingRoomDTO.setTags(tagRepositoryStatic.findByIdIn(tags));
        return meetingRoomDTO;
    }

    public static List<MeetingRoomDTO> convert(List<MeetingRoom> meetingRoomList) {
        List<MeetingRoomDTO> meetingRoomDTOList = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRoomList) {
            meetingRoomDTOList.add(convert(meetingRoom));
        }
        return meetingRoomDTOList;
    }

}
