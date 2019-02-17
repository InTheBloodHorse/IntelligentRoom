package cn.lsu.chicken.room.convert;

import cn.lsu.chicken.room.domain.User;
import cn.lsu.chicken.room.dto.UserDTO;
import cn.lsu.chicken.room.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class User2UserDTO {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private static CompanyService staticCompanyService;

    @PostConstruct
    public void init() {
        staticCompanyService = companyService;
    }

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setCompany(staticCompanyService.getEntityById(user.getCompanyId()));
        return userDTO;
    }
}
