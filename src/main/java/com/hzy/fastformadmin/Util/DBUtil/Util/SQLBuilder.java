package com.hzy.fastformadmin.Util.DBUtil.Util;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        Field[] fields = tClass.getDeclaredFields();
        switch (option) {
            case "insert":
                sql.append(MessageFormat.format("insert into {0} ", tableName));
                try {
                    int i = 0;
                    column.append(" ( ");
                    value.append(" values( ");
                    for (Field field : fields) {
                        field.setAccessible(Boolean.TRUE);
                        if (!field.isAnnotationPresent(Ignore.class)) {
                            if (i != 0) {
                                column.append(" , ");
                                value.append(" , ");
                            }
                            i++;
                            column.append(field.getAnnotation(ColumnName.class).value());
                            value.append(" ? ");
                        }
                    }
                    column.append(" ) ");
                    value.append(" ) ");
                    sql.append(column).append(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                int i = 0;
                sql.append(MessageFormat.format("update {0} set ", tableName));
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(Ignore.class) && !field.isAnnotationPresent(Key.class)) {
                        if (i != 0) {
                            column.append(" , ");
                        }
                        i++;
                        column.append(field.getAnnotation(ColumnName.class).value()).append(" = ? ");
                    }
                }
                sql.append(column);
                sql.append(MessageFormat.format(" where {0}=?", KeyName));
                break;
            case "delete":
                sql.append(MessageFormat.format("delete from {0} ", tableName));
                sql.append(MessageFormat.format(" where {0}=?", KeyName));
                break;
            case "select":
                sql.append(MessageFormat.format("select * from {0} where ", tableName));
                String keyName = ClassUtil.GetkeyfieldName(tClass);
                sql.append(keyName).append(" = ? ");

        }
        return sql;
    }

    public <T> StringBuffer updateParamSqlBuild(List<String> columns, List<String> wheres, String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append(MessageFormat.format("update {0} set ", tableName));
        int i = 0;
        if(columns.size() > 0){
            for (String column : columns) {
                if (i != 0) {
                    sql.append(",");
                }
                i++;
                sql.append(column).append(" = ").append(" ? ");
            }
        }else{
            sql.append(" * ");
        }
        sql.append(whereSqlBuild(wheres));
        return sql;
    }

    public <T> StringBuffer deleteParamSqlBuild(List<String> wheres, String tableName) {
        StringBuffer sql = new StringBuffer();
        sql.append(MessageFormat.format("delete from {0} ", tableName));
        sql.append(whereSqlBuild(wheres));
        return sql;
    }

    public <T> StringBuffer selectParamSqlBuild(List<String> columns, List<String> wheres, String tableName, String type) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        int i = 0;
        if ("param".equals(type) || columns.size() > 0) {
            for (String column : columns) {
                if (i != 0) {
                    sql.append(" , ");
                }
                i++;
                sql.append(column);
            }
        } else {
            sql.append(" * ");
        }
        sql.append(MessageFormat.format(" from {0} ", tableName));
        sql.append(whereSqlBuild(wheres));
        return sql;
    }

    public StringBuffer whereSqlBuild(List<String> wheres) {
        StringBuffer sql = new StringBuffer("");
        if(wheres != null && wheres.size() > 0){
            sql.append(" where ");
            int i = 0;
            for (String where : wheres) {
                if (i != 0) {
                    sql.append(" and ");
                }
                i++;
                sql.append(where).append(" = ").append(" ? ");
            }
        }
        return sql;
    }

    public String mySqlBuild(String sql, Map<String,Object> whereMap) {
        String newSql = "";
        if(whereMap != null){
            Iterator entries = whereMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry map = (Map.Entry)entries.next();
                String paramName = map.getKey().toString();
                newSql = sql.replaceFirst("#"+paramName,"'"+whereMap.get(paramName).toString()+"'");
                sql = newSql;
            }
        }
        return newSql;
    }
}
