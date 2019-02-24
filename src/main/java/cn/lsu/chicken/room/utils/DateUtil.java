package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {
    public static final String MINDATE = "1900-1-1 00:00";
    public static final String MAXDATE = "2100-1-1 00:00";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date min(Date date1) {
        return min(date1, MAXDATE);
    }

    public static Date max(Date date1) {
        return max(date1, MINDATE);
    }

    public static Date min(Date date1, String data2Str) {
        Date date2 = parse(data2Str);
        if (date1 == null) {
            return date2;
        }
        Integer result = date1.compareTo(date2);
        return result > 0 ? date2 : date1;
    }


    public static Date max(Date date1, String data2Str) {
        Date date2 = parse(data2Str);
        if (date1 == null) {
            return date2;
        }
        Integer result = date1.compareTo(date2);
        return result > 0 ? date1 : date2;
    }


    public static Date parse(String dateStr) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.DATE_IS_WRONG);
        }
        return date;
    }
}
