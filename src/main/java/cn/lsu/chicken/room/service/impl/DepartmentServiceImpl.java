package cn.lsu.chicken.room.service.impl;

import cn.lsu.chicken.room.dao.DepartmentMapper;
import cn.lsu.chicken.room.domain.Department;
import cn.lsu.chicken.room.domain.DepartmentExample;
import cn.lsu.chicken.room.dto.DepartmentDTO;
import cn.lsu.chicken.room.dto.PageDTO;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.form.department.DepartmentQueryForm;
import cn.lsu.chicken.room.helper.PageHelper;
import cn.lsu.chicken.room.service.DepartmentService;
import cn.lsu.chicken.room.utils.QueryFormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Integer saveEntity(Department entity) {
        Integer companyId = entity.getCompanyId();
        String name = entity.getName();
        judgeExistByNameAndCompanyId(name, companyId);
        departmentMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Integer updateEntity(Department entity) {
        Integer id = entity.getId();
        String name = entity.getName();
        Integer companyId = entity.getCompanyId();
        Boolean judge = judgeExistByIdAndNameAndCompanyId(id, name, companyId);
        if (judge == false) {
            judgeExistByNameAndCompanyId(name, companyId);
        }
        return departmentMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteEntity(Integer integer) {
        return departmentMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public DepartmentDTO getEntityById(Integer integer) {
        return departmentMapper.selectByPrimaryKey(integer);
    }

    @Override
    public PageDTO<DepartmentDTO> listEntityByQueryForm(DepartmentQueryForm entityQueryForm) {
        Integer page = entityQueryForm.getPage();
        Integer size = entityQueryForm.getSize();
        String order = entityQueryForm.getOrder();
        DepartmentExample example = (DepartmentExample) QueryFormUtil.getExample(DepartmentExample.class, page, size, order);
        DepartmentExample.Criteria criteria = example.createCriteria();
        QueryFormUtil.addFilter(criteria, entityQueryForm, DepartmentQueryForm.QUERTFORMLIST);
        List<DepartmentDTO> data = departmentMapper.selectByExample(example);
        Integer total = departmentMapper.countByExample(example);
        PageHelper pageHelper = example;
        PageDTO<DepartmentDTO> entityPageDTO = new PageDTO<>(pageHelper, total, data);
        return entityPageDTO;
    }


    private void judgeExistByNameAndCompanyId(String name, Integer companyId) {
        DepartmentExample departmentExample = getExample(name, companyId);
        Integer number = departmentMapper.countByExample(departmentExample);
        if (number > 0) {
            throw new GlobalException(ResultEnum.DEPARTMENT_IS_EXIST);
        }
    }

    private Boolean judgeExistByIdAndNameAndCompanyId(Integer id, String name, Integer companyId) {
        DepartmentExample departmentExample = getExample(name, companyId);
        DepartmentExample.Criteria criteria = departmentExample.getOredCriteria().get(0);
        criteria.andIdEqualTo(id);
        Integer result = departmentMapper.countByExample(departmentExample);
        return result > 0 ? true : false;
    }

    private DepartmentExample getExample(String name, Integer companyId) {
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andCompanyIdEqualTo(companyId);
        return departmentExample;
    }
}
