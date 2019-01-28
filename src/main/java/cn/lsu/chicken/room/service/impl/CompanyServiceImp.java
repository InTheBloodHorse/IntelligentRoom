package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.CompanyRepository;
import cn.lsu.chicken.room.entity.Company;
import cn.lsu.chicken.room.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company addCompany(Company company) {
        return null;
    }

    @Override
    public void deleteCompanyById(Integer id) {

    }

    @Override
    public Company updateCompany(Company company) {
        return null;
    }

    @Override
    public Company findCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }
}
