package com.hzy.fastformadmin.Util.DBUtil.Util;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import com.mysql.cj.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassUtil {
    public static <T> T MapToObject(Class<T> tClass, Map<String, Object> value) {
        T obj = null;
        try {
            obj = tClass.newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getDeclaredAnnotation(Ignore.class) == null) {
                    field.setAccessible(Boolean.TRUE);
                    field.set(obj, value.get(field.getAnnotation(ColumnName.class).value()));
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return obj;
        }

    }

    public static <T> String  GetTableName(Class<T> tClass) {
        TableName tableName = tClass.getAnnotation(TableName.class);
        return tableName.value();
    }

    public static <T> String  GetkeyFieldValue(Class<T> tClass) {
        String value = "";
        Field[] fields = tClass.getFields();
        try {
            Object obj = tClass.newInstance();
            for (Field field : fields) {
                if (field.getDeclaredAnnotation(Key.class)!= null) {
                    value = field.get(obj).toString();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

    public static <T> String  GetkeyfieldName(Class<T> tClass) {
        String keyName = "";
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Key.class)!= null) {
                keyName = field.getAnnotation(ColumnName.class).value();
                break;
            }
        }
        return keyName;

    }

    public static <T> List<Object> EntityValueToArray(T t) {
        List<Object> result = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(Boolean.TRUE);
                if (!field.isAnnotationPresent(Ignore.class)) {
                    result.add(field.get(t));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public static <T> String fieldToColumnName(Class<T> tClass,String field) {
        String columnName = "";
        if(!StringUtils.isNullOrEmpty(field)){
            Field objectField = null;
            try {
                objectField = tClass.getDeclaredField(field);
                objectField.setAccessible(Boolean.TRUE);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            columnName = objectField.getAnnotation(ColumnName.class).value();
            return columnName;
        }else{
            System.out.println("ClassUtil.fieldToColumnName传入参数field为空");
        }
        return columnName;
    }


}

