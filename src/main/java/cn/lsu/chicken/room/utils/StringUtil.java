package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    public static String FirstUpper(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= ('a' - 'A');
        }
        return String.valueOf(chars);
    }


    public static List<Integer> string2IntegerList(String string) {
        List<String> stringList = Arrays.asList(string.split(","));
        return stringList2IntegerList(stringList);
    }

    public static List<Integer> stringList2IntegerList(List<String> strings) {
        List<Integer> result = null;
        try {
            result = strings.stream().map(
                    e -> Integer.parseInt(e.trim())
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        return result;
    }

    public static List<BigDecimal> stringList2BigDecimalList(List<String> strings) {
        List<BigDecimal> result = null;
        try {
            result = strings.stream().map(
                    e -> new BigDecimal(e.trim())
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        return result;
    }

    public static List<Date> stringList2DatelList(List<String> strings) {
        List<Date> result = null;
        try {
            result = strings.stream().map(
                    e -> DateUtil.parse(e)
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        return result;
    }
}
