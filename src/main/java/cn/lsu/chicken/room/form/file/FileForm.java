package cn.lsu.chicken.room.form.file;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FileForm {
    @NotNull(message = "请输入文件编号")
    private Integer id;

    @NotBlank(message = "文件名不能为空")
    private String fileName;
}
