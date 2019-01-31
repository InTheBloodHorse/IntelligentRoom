package cn.lsu.chicken.room.utils;

import java.util.Random;

public class KeyUtil {

    public static Integer preLen = String.valueOf(System.currentTimeMillis()).length();
    public static Integer sufLen = 6;

    public static Integer companyLen = 4;


    public static String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(9 * (int) Math.pow(10, sufLen - 1)) + (int) Math.pow(10, sufLen - 1);
        return System.currentTimeMillis() + String.valueOf(number);
    }


    public static String companyCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(9 * (int) Math.pow(10, companyLen - 1)) + (int) Math.pow(10, companyLen - 1));
    }

}
