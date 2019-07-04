package com.hzy.fastformadmin.Util.DBUtil.Util;

import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.sun.javafx.binding.StringFormatter;

import java.lang.reflect.Field;
import java.text.MessageFormat;
/*
 * 实现基本的sql语句
 */
public class SQLBuilder {
    public <T> StringBuilder updateBuild(Class<T> tClass, String option) {
        String tableName = ClassUtil.GetTableName(tClass);
        String KeyName = ClassUtil.GetkeyfieldName(tClass);
        StringBuilder sql = new StringBuilder();
        StringBuilder column = new StringBuilder();
        StringBuilder value = new StringBuilder();
        Field[] fields = tClass.getClass().getDeclaredFields();
        switch (option) {
            case "insert":
                sql.append(MessageFormat.format("insert into {0} ", tableName));
                try {

                    column.append(" ( ");
                    value.append(" values( ");
                    for (Field field : fields) {
                        if (field.getDeclaredAnnotation(Ignore.class) == null) {
                            column.append("," + field.getName());
                            value.append(" , ?");
                        }
                    }
                    column.deleteCharAt(0);
                    value.deleteCharAt(0);
                    column.append(" ) ");
                    value.append(" ) ");
                    sql.append(column).append(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                sql.append(MessageFormat.format("update {0} set ", tableName));
                for (Field field : fields) {
                    if (field.getDeclaredAnnotation(Ignore.class) == null) {
                        column.append("," + field.getName() + " = ? ");
                    }
                }
                column.deleteCharAt(0);
                sql.append(column);
                sql.append(MessageFormat.format(" where {0}=?",KeyName));
                break;
            case "delete":
                sql.append(MessageFormat.format("delete from {0} ", tableName));
                sql.append(MessageFormat.format(" where {0}=?",KeyName));
                break;
        }
        return sql;
    }
}
