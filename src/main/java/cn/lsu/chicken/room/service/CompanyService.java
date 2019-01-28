package cn.lsu.chicken.room.service;

import cn.lsu.chicken.room.entity.Company;

public interface CompanyService {
    //添加公司信息
    Company addCompany(Company company);

    //删除公司
    void deleteCompanyById(Integer id);

    //编辑公司信息
    Company updateCompany(Company company);

    //根据公司编号查找
    Company findCompanyById(Integer id);
}
