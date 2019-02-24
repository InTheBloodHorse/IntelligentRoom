package cn.lsu.chicken.room.form.company;

import cn.lsu.chicken.room.domain.Company;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CompanyForm {

    @NotNull(message = "公司编号不能为空")
    private Integer id;

    @NotBlank
    @NotNull(message = "公司名称不能为空")
    private String name;

    public Company convert() {
        Company company = new Company();
        BeanUtils.copyProperties(this, company);
        return company;
    }
}
