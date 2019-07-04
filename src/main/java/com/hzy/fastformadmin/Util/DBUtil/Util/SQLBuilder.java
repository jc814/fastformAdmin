package com.hzy.fastformadmin.Util.DBUtil.Util;

import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.sun.javafx.binding.StringFormatter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

public class SQLBuilder {
    public <T> String Build(Class<T> tClass, String option) {
        String tableName = ClassUtil.GetTableName(tClass);
        String KeyName = ClassUtil.GetTableName(tClass);
        StringBuilder sql = new StringBuilder();
        StringBuilder column = new StringBuilder();
        StringBuilder value = new StringBuilder();
        switch (option) {
            case "insert":
                sql.append(MessageFormat.format("insert into {0} ", tableName));
                try {
                    T obj = tClass.newInstance();
                    Field[] fields = tClass.getClass().getDeclaredFields();
                    column.append(" ( ");
                    value.append(" ( ");
                    for (Field field : fields) {
                        if (field.getDeclaredAnnotation(Ignore.class) == null) {
                            column.append("," + field.getName());
                            value.append(" , ?");
                        }
                    }
                    column.deleteCharAt(0)
                    value.deleteCharAt(0)
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for ()
                    ; break;
            case "update":
                break;
            case "delete":
                break;
        }
    }
}
