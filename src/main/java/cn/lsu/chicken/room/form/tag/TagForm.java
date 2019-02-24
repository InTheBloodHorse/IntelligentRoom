package cn.lsu.chicken.room.form.tag;

import cn.lsu.chicken.room.domain.Tag;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TagForm {

    @NotNull(message = "编号不能为空")
    private Integer id;

    @NotBlank(message = "标签名不能为空")
    private String name;

    public Tag convert() {
        Tag tag = new Tag();
        BeanUtils.copyProperties(this, tag);
        return tag;
    }
}
