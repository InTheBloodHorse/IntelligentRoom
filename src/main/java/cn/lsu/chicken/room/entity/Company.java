package cn.lsu.chicken.room.entity;

import cn.lsu.chicken.room.utils.KeyUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //公司名称
    private String name;
    //公司HR ,可以多个
    private String hr;
    //公司唯一邀请码
    private String code = KeyUtil.companyCode();
    //花费金额
    private BigDecimal cost = new BigDecimal(0);
}
