package com.benma.graphql.Utils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * map转实体类
 */
public class ToEntityUtil {

    public static Object entity(Object model, LinkedHashMap linkedHashMap) {

        Set keySet = linkedHashMap.keySet();
        Field[] fields = model.getClass().getDeclaredFields();//所有的字段

        String[] fieldNames = new String[fields.length];//初始化字段名称数组
        String[] fieldTypes = new String[fields.length];//初始化字段类型数组
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
            fieldTypes[i] = fields[i].getGenericType().toString();
            fields[i].setAccessible(true);//获取访问私有变量的权限
            for (Object key : keySet) {
                if (fieldNames[i].equalsIgnoreCase(key.toString())) {
                    try {
                        fields[i].set(model, linkedHashMap.get(key));//给字段设置属性
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return model;
    }
}
