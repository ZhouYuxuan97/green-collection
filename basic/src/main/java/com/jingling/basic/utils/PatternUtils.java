package com.jingling.basic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @Description: 使用正则表达式匹配字符串的工具类
 * @Auther: zyy-finalcola
 * @Date: 2018-01-24 19:34
 */
public class PatternUtils {

    /**
     * 返回 original字符串中匹配正则的字符（matcher.group(groupId) ）
     * @param original
     * @param regex
     * @param groupId
     * @return
     */
    public static String getPatternStr(String original, String regex,int groupId) {
        if (original == null || "".equals(original)) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(original);
        if (matcher.find()) {
            try {
                String result = matcher.group(groupId);
                return result;
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return null;
    }
}
