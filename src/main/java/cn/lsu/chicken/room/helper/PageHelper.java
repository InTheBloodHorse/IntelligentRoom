package cn.lsu.chicken.room.helper;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import lombok.Data;

@Data
public class PageHelper {
    Integer page;
    Integer size;
    Integer offset;

    public PageHelper() {
    }

    public PageHelper(Integer page, Integer size) {
        this.page = page - 1;
        this.size = size;
        Boolean condition = this.page < 0 || this.size <= 0;
        if (condition) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        this.offset = this.page * this.size;
    }
}
