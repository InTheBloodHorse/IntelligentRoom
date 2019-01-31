package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CompanyService {
    //保存公司信息
    void saveCompany(Company company);

    //删除公司
    void deleteCompanyById(Integer id);

    //收费
    Company increaseCost(Integer id, BigDecimal cost);

    //根据公司编号查找
    Company findCompanyById(Integer id);

    //查看所有公司
    List<Company> getAllCompany();

    PageDTO<Company> getAllCompany(Pageable pageable);
}
