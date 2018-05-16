package com.jingling.basic.utils;

import java.lang.reflect.Field;

/**
 *
 * @Description: 验证字符串是否含有不合法内容的工具类
 * @Auther: zyy-finalcola
 * @Date: 2018-01-24 19:34
 */
public class NameValidUtils {

    /**
     * 验证对象中的String字段是否带有违规内容
     * @param object
     * @return
     */
    public static boolean validStr(Object object) throws IllegalAccessException {
        Class<?> klass = object.getClass();
        if (klass.isArray()) {
            //数组类型
            try {
                Object[] array = (Object[]) object;
                if (array.length == 0) {
                    return true;
                }
                Class<?> itemClass = array[0].getClass();
                Field[] fields = itemClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                }

                for (Object o : array) {
                    for (Field field : fields) {
                        try {
                            Object value = field.get(o);
                            if (value instanceof String) {
                                boolean passed = validStr(value);
                                if (!passed) {
                                    return false;
                                }
                            }
                        } catch (IllegalArgumentException|IllegalAccessException e) {
                            throw e;
                        }
                    }
                }
            } catch (Exception e) {
                throw e;
            }

        } else {
            Field[] fields = klass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (value instanceof String) {
                        boolean passed = validStr(value);
                        if (!passed) {
                            return passed;
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw e;
                }

            }
        }
        return true;
    }

    /**
     * 验证输入的字符串是否带有违规内容
     * @param name
     * @return
     */
    public static boolean validStr(String name) {
        return true;
    }

    public static void main(String[] args) {
        String[] strings = {"123", "456"};
        Class<?> componentType = strings.getClass().getComponentType();
        System.out.println(componentType);
        Object object = strings;
        Object[] array = (Object[]) object;
        for (Object o : array) {
            System.out.println(o);
        }
    }
}
