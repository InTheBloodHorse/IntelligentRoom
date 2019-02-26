package cn.lsu.chicken.room.form.service;

import cn.lsu.chicken.room.domain.Service;
import cn.lsu.chicken.room.form.BaseQueryForm;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddServiceForm {

    @NotNull(message = "会议编号不能为空")
    private Integer applyId;

    @NotNull(message = "员工编号不能为空")
    private Integer workerId;

    @NotBlank(message = "内容不能为空")
    private String content;

    public Service convert() {
        Service service = new Service();
        BeanUtils.copyProperties(this, service);
        return service;
    }
}
