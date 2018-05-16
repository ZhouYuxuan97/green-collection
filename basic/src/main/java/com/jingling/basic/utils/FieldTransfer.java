package com.jingling.basic.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @Description: 将一个对象的属性赋值到另一个对象的同名属性中
 * @Auther: zyy-finalcola
 * @Date: 2018-01-24 19:32
 */
public class FieldTransfer {

    /**
     * 将对象中string值为""的属性设置为null
     * @param object
     */
    public static void transStrToNull(Object object) {
        if (object == null) {
            return;
        }
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //筛选出类型为String的field
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value != null && value.getClass().equals(String.class)) {
                    if ("".equals(value)) {
                        field.set(object,null);
                    }
                }
            } catch (IllegalAccessException e) {

            }

        }
    }

    /**
     * 将一个实例的属性赋值到另一个对象的同名属性中
     * <p><strong>复制的字段必须有对应的get和set方法</strong></p>
     * @param from  带有值的实例
     * @param to    待赋值的实例
     * @return
     */
    public static int fieldTrans(Object from, Object to) throws IllegalAccessException {
        int count = 0;//成功复制的个数
        Class fromClass = from.getClass();
        Class toClass = to.getClass();
        Field[] fromFields = fromClass.getDeclaredFields();
        Field[] toFields = toClass.getDeclaredFields();
        List<Field> fromFieldList = Arrays.asList(fromFields);
        boolean[] flags = new boolean[toFields.length];
        for (Field item : fromFieldList) {
            if (!item.isAccessible()) {
                item.setAccessible(true);
            }
            String fromName = item.getName();
            Object value = null;
            try {
                value = item.get(from);
                if (value != null) {
                    Field toField = toClass.getDeclaredField(fromName);
                    if (toField != null && item.equals(toField)) {
                        if (!toField.isAccessible()) {
                            toField.setAccessible(true);
                        }
                        toField.set(to,value);
                    }
                }
            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
            }

        }

        return count;
    }

}
