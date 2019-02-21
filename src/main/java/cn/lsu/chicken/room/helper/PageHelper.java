package cn.lsu.chicken.room.helper;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.utils.StringUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Data
public class PageHelper {
    Integer page;
    Integer size;
    Integer offset;
    List<String> orderList;

    public PageHelper() {
    }

    public PageHelper(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        Boolean condition = this.page < 0 || this.size <= 0;
        if (condition) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        this.offset = (this.page - 1) * this.size;
    }

    public void setOrderList(String orderList) {
        if (StringUtils.isEmpty(orderList)) {
            this.orderList = null;
        } else {
            this.orderList = Arrays.asList(orderList.split(","));
            preventSqlInjection();
        }
    }

    private void preventSqlInjection() {
        List<String> list = orderList;
        for (String o : list) {
            String[] strings = o.split(" ");
            if (strings.length != 2) {
                throw new GlobalException(ResultEnum.PARAMETER_ERROR);
            }
            String prefix = strings[0];
            String subfix = strings[1];
            if (!subfix.toUpperCase().equals("ASC") || !subfix.toUpperCase().equals("DESC")) {
                throw new GlobalException(ResultEnum.PARAMETER_ERROR);
            }
            for (char c : prefix.toCharArray()) {
                Boolean judge = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
                if (judge == false) {
                    throw new GlobalException(ResultEnum.PARAMETER_ERROR);
                }
            }
        }
    }
}
