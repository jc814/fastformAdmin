package com.hzy.fastformadmin.Util.DBUtil.Util;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;

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
                keyName = field.getName();
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
                if (!field.isAnnotationPresent(Ignore.class)) {
                    result.add(field.get(t));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public static <T> String fieldToColumnName(Class<T> tClass,String field) {
        Field objectField = null;
        try {
            objectField = tClass.getField(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        String columnName = objectField.getAnnotation(ColumnName.class).value();
        return columnName;
    }


}

