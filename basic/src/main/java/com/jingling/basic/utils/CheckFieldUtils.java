package com.jingling.basic.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by final on 17-7-25.
 */
public class CheckFieldUtils {

    /**
     * 检查传入对象的属性field是否为null
     * <p><如果field为null的数量 超过允许的空值数量，返回false/p>
     * @param o 受检查对象
     * @param emptyCount 允许有emptyCount个field为空
     * @return
     */
    public static boolean checkField(Object o,int emptyCount) {
        emptyCount = (emptyCount < 0) ? 0 : emptyCount;//至少为0

        int count = 0;
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//            System.out.println(methodName);
            try {
                Method getMethod = clazz.getDeclaredMethod(methodName);
                try {
                    Object obj = getMethod.invoke(o);
                    if (obj == null) {
                        //如果get方法的返回值为空，表示属性不完整
                        count++;
                        if (count>emptyCount)
                            //如果超过允许的空值数量，返回false
                            return false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * 检查传入对象是否有leastCount个属性不为空
     * @param object
     * @param leastCount
     * @return
     */
    public static boolean leastField(Object object, final int leastCount) {
        if (leastCount <= 0) {
            return true;
        }

        int count = 0;
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//            System.out.println(methodName);
            try {
                Method getMethod = clazz.getDeclaredMethod(methodName);
                try {
                    Object obj = getMethod.invoke(object);
                    if (obj != null) {
//                        System.out.println(fieldName);
                        //如果get方法的返回值为空，表示属性不完整
                        count++;
                        if (count >= leastCount) {
                            //如果超过允许的空值数量，返回false
                            return true;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}
