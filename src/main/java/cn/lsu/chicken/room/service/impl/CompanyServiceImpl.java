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
import cn.lsu.chicken.room.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Integer id = entity.getId();
        String name = entity.getName();
        //假如当前标签修改了名称

        if (judgeExistByIdAndName(id, name) == false) {
            //校验名称是否合法
            judgeExistByName(name);
        }

        return companyMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return companyMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public CompanyDTO getEntityById(Integer integer) {
        return companyMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<CompanyDTO> listEntityByQueryForm(Object entityQueryForm) {
        return null;
    }

//    @Override
//    public List<CompanyDTO> listEntity() {
//        return companyMapper.selectByExample(new CompanyExample());
//    }
//
//    @Override
//    public PageDTO<CompanyDTO> listEntityByPage(PageHelper pageHelper) {
//        CompanyExample companyExample = new CompanyExample(pageHelper.getPage(), pageHelper.getSize());
//        List<CompanyDTO> data = companyMapper.selectByExample(companyExample);
//        Integer total = companyMapper.countByExample(new CompanyExample());
//        PageDTO<CompanyDTO> pageDTO = new PageDTO<>(pageHelper, total, data);
//        return pageDTO;
//    }


    @Override
    public Integer increaseCost(Integer id, BigDecimal cost) {
        return companyMapper.increaseCostByPrimaryKey(id, cost);
    }

    @Override
    public Integer clearCost(Integer id) {
        return companyMapper.clearCostByPrimaryKey(id);
    }

    @Override
    public Integer updateHr(Integer companyId, List<Integer> userList) {
        return companyMapper.updateCompanyHr(companyId, userList);
    }

    @Override
    public Integer getCompanyIdByCode(String code) {
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andCodeEqualTo(code);
        List<CompanyDTO> companyDTOS = companyMapper.selectByExample(companyExample);
        Integer companyId = null;
        if (companyDTOS.size() > 0) {
            companyId = companyDTOS.get(0).getId();
        }
        return companyId;
    }


    private void judgeExistByName(String name) {
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andNameEqualTo(name);
        Integer result = companyMapper.countByExample(companyExample);
        if (result != 0) {
            throw new GlobalException(ResultEnum.COMPANY_IS_EXIST);
        }
    }

    private Boolean judgeExistByCode(String code) {
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andCodeEqualTo(code);
        List<CompanyDTO> result = companyMapper.selectByExample(companyExample);
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

    private Boolean judgeExistByIdAndName(Integer id, String name) {
        if (id == null) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andNameEqualTo(name);
        Integer result = companyMapper.countByExample(companyExample);
        return result > 0 ? true : false;
    }
}
