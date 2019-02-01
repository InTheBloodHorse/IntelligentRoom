package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.entity.User;
import cn.lsu.chicken.room.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class User2UserDTO {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private static CompanyService service;

    @PostConstruct
    public void init() {
        service = companyService;
    }

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setCompany(service.findCompanyById(user.getCompanyId()));
        return userDTO;
    }

    public static List<UserDTO> convert(List<User> users) {
        return users.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
