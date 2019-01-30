package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.Company;

public interface CompanyService {
    //保存公司信息
    Company saveCompany(Company company);

    //删除公司
    void deleteCompanyById(Integer id);


    //根据公司编号查找
    Company findCompanyById(Integer id);
}
