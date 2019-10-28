package com.zhxshark.fileexplorer.commen.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhuxin
 * @date 2019/10/22 9:59
 */
public class ObjectUtils {

    /**
     * 获取属性名数组
     * @param o
     * @return
     */
    public static String[] getFieldNames(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i=0; i<fields.length; i++){
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名称获取对应的属性值（通过get方法）
     * @param fieldName
     * @param o
     * @return
     */
    public static Object getFieldValueByName(String fieldName, Object o){
        try {
            String firstLetter = fieldName.substring(0,1).toUpperCase();
            String getter = "get"+firstLetter+fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        }catch (Exception e){
            return null;
        }
    }

}
