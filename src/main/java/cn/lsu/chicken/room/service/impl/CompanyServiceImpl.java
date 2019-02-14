package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.CompanyMapper;
import cn.lsu.chicken.room.domain.Company;
import cn.lsu.chicken.room.domain.CompanyExample;
import cn.lsu.chicken.room.dto.CompanyDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.utils.EntityExampleUtil;
import cn.lsu.chicken.room.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Integer saveEntity(Company entity) {
        String name = entity.getName();
        judgeExistByName(name);
        genCompanyCode(entity);
        companyMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(Company entity) {
        return null;
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return null;
    }

    @Override
    public CompanyDTO getEntityById(Integer integer) {
        return null;
    }

    @Override
    public List<CompanyDTO> listEntity() {
        return null;
    }

    @Override
    public PageDTO<CompanyDTO> listEntityByPage(PageHelper pageHelper) {
        return null;
    }

    private void judgeExistByName(String name) {
//        CompanyExample companyExample = new CompanyExample();
//        CompanyExample.Criteria criteria = companyExample.createCriteria();
//        criteria.andNameEqualTo(name);
//        List<CompanyDTO> result = companyMapper.selectByExample(companyExample);
//        EntityExampleUtil.getEntityExample("CompanyExample");
        List<Company> result = companyMapper.selectByExample((CompanyExample) EntityExampleUtil.getEntityExample("CompanyExample", name));
        System.out.println(result);
        if (result.size() != 0) {
            throw new GlobalException(ResultEnum.COMPANY_IS_EXIST);
        }
    }

    private Boolean judgeExistByCode(String code) {
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andCodeEqualTo(code);
//        List<CompanyDTO> result = companyMapper.selectByExample(companyExample);
        List<Company> result = companyMapper.selectByExample(companyExample);
        if (result.size() == 0) {
            return false;
        }
        return true;
    }

    private void genCompanyCode(Company company) {
        String code = null;
        do {
            code = KeyUtil.companyCode();
        } while (judgeExistByCode(code));
        company.setCode(code);
    }
}
