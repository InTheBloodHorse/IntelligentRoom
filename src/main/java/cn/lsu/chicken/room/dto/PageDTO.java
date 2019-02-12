package cn.lsu.chicken.room.dto;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.PageHelper;
import lombok.Data;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

@Data
public class PageDTO<T> {
    private Integer page;
    private Integer size;
    private Integer total;
    private Integer totalPage;
    List<T> data;

    public PageDTO() {
    }

    public PageDTO(PageHelper pageHelper, Integer total, List<T> data) {
        this.page = pageHelper.getPage();
        this.size = pageHelper.getSize();
        this.total = total;
        this.totalPage = (int) Math.ceil(((double)getTotal() / (double)getSize()));
        this.data = data;
    }
}
