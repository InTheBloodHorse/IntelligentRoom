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


    public PageHelper(Integer page, Integer size, String order) {
        this.page = page;
        this.size = size;
        Boolean condition = (page != null && size != null) && (this.page <= 0 || this.size <= 0);
        if (condition) {
            this.page = null;
            this.size = null;
        }
        if (page != null && size != null) {
            this.offset = (this.page - 1) * this.size;
        }
        setOrderList(order);
    }

    protected void setOrderList(String order) {
        if (StringUtils.isEmpty(order)) {
            this.orderList = null;
        } else {
            this.orderList = Arrays.asList(order.split(","));
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
            if (!subfix.toUpperCase().equals("ASC") && !subfix.toUpperCase().equals("DESC")) {
                throw new GlobalException(ResultEnum.PARAMETER_ERROR);
            }
            for (char c : prefix.toCharArray()) {
                Boolean judge = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                        || (c == '_') || (c >= '0' && c <= '9');
                if (judge == false) {
                    throw new GlobalException(ResultEnum.PARAMETER_ERROR);
                }
            }
        }
    }
}
