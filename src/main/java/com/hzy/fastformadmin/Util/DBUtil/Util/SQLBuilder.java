package com.hzy.fastformadmin.Util.DBUtil.Util;

import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

/*
 * 实现基本的sql语句
 */
public class SQLBuilder {

    public <T> StringBuilder baseSqlBuild(Class<T> tClass, String option) {
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
                    if (field.isAnnotationPresent(Ignore.class) || field.isAnnotationPresent(Key.class)) {
                        column.append(",").append(field.getName()).append(" = ? ");
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
            case "select":
                sql.append(MessageFormat.format("select * from {0} where ", tableName));
                String keyName = ClassUtil.GetkeyfieldName(tClass);
                sql.append(keyName).append(" = ? ");

        }
        return sql;
    }
    public StringBuffer updateParamSqlBuild(List<String> columns,List<String> wheres,String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append(MessageFormat.format("update {0} set ", tableName));
        int i = 0;
        int j = 0;
        for(String column : columns){
            if(i != 0){
                sql.append(",");
            }
            i++;
            sql.append(column).append(" = ").append(" ? ");
        }
        sql.append(" where ");
        for(String where : wheres){
            if(j != 0){
                sql.append(" and ");
            }
            j++;
            sql.append(where).append(" = ").append(" ? ");
        }
        return sql;
    }
    public StringBuffer deleteParamSqlBuild(List<String> wheres,String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append(MessageFormat.format("delete from {0} ", tableName));
        sql.append(" where ");
        int j = 0;
        for(String where : wheres){
            if(j != 0){
                sql.append(" and ");
            }
            j++;
            sql.append(where).append(" = ").append(" ? ");
        }
        return sql;
    }
    public StringBuffer selectParamSqlBuild(List<String> columns,List<String> wheres,String tableName,String type) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        int i = 0;
        int j = 0;
        if("param".equals(type)){
            for(String column : columns){
                if(i != 0){
                    sql.append(" , ");
                }
                i++;
                sql.append(column);
            }
        }
        sql.append(MessageFormat.format(" from {0} ", tableName));
        for(String where : wheres){
            if(j != 0){
                sql.append(" and ");
            }
            j++;
            sql.append(where).append(" = ").append(" ? ");
        }
        return sql;
    }
}
