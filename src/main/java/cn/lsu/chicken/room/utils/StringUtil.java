package cn.lsu.chicken.room.utils;

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
}
