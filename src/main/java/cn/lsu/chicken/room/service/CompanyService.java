package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.domain.Company;
import cn.lsu.chicken.room.dto.CompanyDTO;
import cn.lsu.chicken.room.dto.PageDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CompanyService extends BaseService<CompanyDTO, Company, Integer,Object> {

    // 收费
    Integer increaseCost(Integer id, BigDecimal cost);

    // 费用清零
    Integer clearCost(Integer id);

    Integer updateHr(Integer companyId, List<Integer> userList);

    Integer getCompanyIdByCode(String code);

}
