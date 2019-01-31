package cn.lsu.chicken.room.dao;

import cn.lsu.chicken.room.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
