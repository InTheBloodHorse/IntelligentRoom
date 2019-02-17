package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.domain.Company;
import cn.lsu.chicken.room.domain.CompanyExample;

import java.math.BigDecimal;
import java.util.List;

import cn.lsu.chicken.room.dto.CompanyDTO;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Component;

@Component
public interface CompanyMapper {
    int countByExample(CompanyExample example);

    int deleteByExample(CompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    List<CompanyDTO> selectByExample(CompanyExample example);

    CompanyDTO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    int updateCompanyHr(@Param("companyId") Integer companyId, @Param("list") List<Integer> userList);

    int increaseCostByPrimaryKey(@Param("id") Integer companyId, @Param("cost") BigDecimal cost);

    int clearCostByPrimaryKey(@Param("id") Integer companyId);
}