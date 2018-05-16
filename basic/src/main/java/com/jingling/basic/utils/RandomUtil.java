package com.jingling.basic.utils;

import java.util.Random;

/**
 *
 * @Description: 生成随机数的工具类
 * @Auther: zyy-finalcola
 * @Date: 2018-01-24 19:35
 */
public class RandomUtil {

    public static final Random random = new Random();

    public static int getRandInt(int start, int end) {
        return random.nextInt(end - start) + start;
    }

    public static char main(String[] args) {
        int start = 'a';
        int end = 'z';
        boolean toUpper = random.nextBoolean();
        char result = (char) getRandInt(start, end);
        if (toUpper) {
            result = Character.toUpperCase(result);
        }
        return result;
    }
}
