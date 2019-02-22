package cn.lsu.chicken.room.form.tag;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TagForm {

    @NotNull(message = "编号不能为空")
    private Integer id;

    @NotBlank(message = "标签名不能为空")
    private String name;
}
