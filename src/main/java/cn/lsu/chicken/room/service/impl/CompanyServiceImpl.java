package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.CompanyMapper;
import cn.lsu.chicken.room.domain.Company;
import cn.lsu.chicken.room.domain.CompanyExample;
import cn.lsu.chicken.room.domain.Weight;
import cn.lsu.chicken.room.dto.CompanyDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.company.CompanyQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.CompanyService;
import cn.lsu.chicken.room.service.WeightService;
import cn.lsu.chicken.room.utils.KeyUtil;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private WeightService weightService;

    @Override
    public Integer saveEntity(Company entity) {
        String name = entity.getName();
        judgeExistByName(name);
        genCompanyCode(entity);
        companyMapper.insertSelective(entity);
        // 在weight（信用表）插入公司数据
        Weight weight = new Weight();
        weight.setCompanyId(entity.getId());
        weightService.saveEntity(weight);
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

        return companyMapper.updateByPrimaryKeySelective(entity);
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
    public PageDTO<CompanyDTO> listEntityByQueryForm(CompanyQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        CompanyExample example = (CompanyExample) QueryFormUtil.getExample(CompanyExample.class, page, size, order);
        CompanyExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, CompanyQueryForm.QUERTFORMLIST);
        List<CompanyDTO> data = companyMapper.selectByExample(example);
        Integer total = companyMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<CompanyDTO> tagPageDTO = new PageDTO<>(pageHelper, total, data);
        return tagPageDTO;
    }


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
