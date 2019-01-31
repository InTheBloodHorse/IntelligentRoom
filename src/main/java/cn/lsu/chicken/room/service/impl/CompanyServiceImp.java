package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.CompanyRepository;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.entity.Company;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public void saveCompany(Company company) {
        //保存
        if (company.getId() == null) {
            if (companyRepository.existsByName(company.getName())) {
                throw new GlobalException(ResultEnum.COMPANY_IS_EXITS);
            }
            while (companyRepository.existsByCode(company.getCode())) {
                company.setCode(KeyUtil.companyCode());
            }
        } else {
            Company result = companyRepository.findById(company.getId()).orElse(null);
            if (result == null) {
                throw new GlobalException(ResultEnum.COMPANY_NOT_EXITS);
            }
            if (!company.getName().equals(result.getName())) {
                if (companyRepository.existsByName(company.getName())) {
                    throw new GlobalException(ResultEnum.COMPANY_IS_EXITS);
                }
            }
        }
        companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Integer id) {
        try {
            companyRepository.deleteById(id);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.COMPANY_NOT_EXITS);
        }
    }

    @Override
    public Company increaseCost(Integer id, BigDecimal cost) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            throw new GlobalException(ResultEnum.COMPANY_NOT_EXITS);
        }
        company.setCost(company.getCost().add(cost));
        Company result = companyRepository.save(company);
        return result;
    }

    @Override
    public Company findCompanyById(Integer id) {
        if(id==null){
            return null;
        }
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public PageDTO<Company> getAllCompany(Pageable pageable) {
        Page<Company> page = companyRepository.findAll(pageable);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(pageable.getPageNumber());
        pageDTO.setSize(pageable.getPageSize());
        pageDTO.setTotal(page.getTotalElements());
        pageDTO.setTotalPage(page.getTotalPages());
        pageDTO.setData(page.getContent());
        return pageDTO;
    }
}
