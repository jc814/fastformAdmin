package com.hzy.fastformadmin.Util.DBUtil.base;

import com.hzy.fastformadmin.Util.DBUtil.Util.ClassUtil;
import com.hzy.fastformadmin.Util.DBUtil.Util.SQLBuilder;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Ignore;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;

import java.lang.reflect.Field;
import java.util.*;

public class PreCommand {
    private SQLBuilder sqlBuilder;
    public PreCommand() {
        if(sqlBuilder == null){
            sqlBuilder = new SQLBuilder();
        }
    }
    public <T> Command initDelete(Class<T> tClass,String primaryKey){
        StringBuilder sql = sqlBuilder.baseSqlBuild(tClass,"delete");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        List<Object> param = new ArrayList<Object>();
        param.add(primaryKey);
        command.setSQLParams(param);
        return command;
    }

    public <T> Command initInsert(T entity){
        StringBuilder sql = sqlBuilder.baseSqlBuild(entity.getClass(),"insert");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(ClassUtil.EntityValueToArray(entity));
        return command;
    }

    public <T> Command initUpdate(T entity){
        StringBuilder sql = sqlBuilder.baseSqlBuild(entity.getClass(),"update");
        List<Object> param = new ArrayList<Object>();
        Field[] fields = entity.getClass().getDeclaredFields();
        String keyValue = "";
        try {
            for (Field field : fields) {
                field.setAccessible(Boolean.TRUE);
                if (!field.isAnnotationPresent(Ignore.class) && !field.isAnnotationPresent(Key.class)) {
                    param.add(field.get(entity));
                }
                if(field.isAnnotationPresent(Key.class)){
                    keyValue = field.get(entity).toString();
                }
            }
            param.add(keyValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(param);
        return command;
    }

    public <T> Command initSelectOne(Class<T> tClass,String primaryKey){
        StringBuilder sql = sqlBuilder.baseSqlBuild(tClass,"select");
        List<Object> param = new ArrayList<>();
        param.add(primaryKey);
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(param);
        return command;
    }

    public <T> Command initUpdateByParam(Class<T> tClass,Map<String,Object> columnMap,Map<String,Object> whereMap){
        List<String> columnKeys = (List<String>)mapToList(tClass,columnMap).get("keys");
        List<Object> columnValues = (List<Object>)mapToList(tClass,columnMap).get("values");
        List<String> whereKeys = (List<String>)mapToList(tClass,whereMap).get("keys");
        List<Object> whereValues = (List<Object>)mapToList(tClass,whereMap).get("values");
        String tableName = tClass.getAnnotation(TableName.class).value();
        StringBuffer sql = sqlBuilder.updateParamSqlBuild(columnKeys,whereKeys,tableName);
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        columnValues.addAll(whereValues);
        command.setSQLParams(columnValues);
        return command;
    }

    public <T> Command initFindFieldByParam(Class<T> tClass,List<String> fields,Map<String,Object> whereMap){
        List<String> columns = getColumnList(tClass,fields);
        List<String> whereKeys = (List<String>)mapToList(tClass,whereMap).get("keys");
        List<Object> whereValues = (List<Object>)mapToList(tClass,whereMap).get("values");
        String tableName = tClass.getAnnotation(TableName.class).value();
        StringBuffer sql = sqlBuilder.selectParamSqlBuild(columns,whereKeys,tableName,"param");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(whereValues);
        return command;
    }

    public <T> Command initDelOrFindAllByParam(Class<T> tClass,Map<String,Object> whereMap,String type){
        List<String> whereKeys = (List<String>)mapToList(tClass,whereMap).get("keys");
        List<Object> whereValues = (List<Object>)mapToList(tClass,whereMap).get("values");
        StringBuffer sql;
        String tableName = tClass.getAnnotation(TableName.class).value();
        if("delete".equals(type)){
            sql = sqlBuilder.deleteParamSqlBuild(whereKeys,tableName);
        }else{
            sql = sqlBuilder.selectParamSqlBuild(null,whereKeys,tableName,"all");
        }
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(whereValues);
        return command;
    }

    public <T> Command initMySqlByParam(String sql ,Map<String,Object> whereMap){
        String newSql = sqlBuilder.mySqlBuild(sql,whereMap);
        Command command = CommandFactory.getCommand();
        command.setSQLText(newSql);
        return command;
    }

    private <T> Map<String,Object> mapToList(Class<T> tClass,Map<String,Object> map){
        Map<String,Object> result = new HashMap<>();
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry columnEntry = (Map.Entry)entries.next();
            keys.add(ClassUtil.fieldToColumnName(tClass,columnEntry.getKey().toString()));
            values.add(columnEntry.getValue());
        }
        result.put("keys",keys);
        result.put("values",values);
        return result;
    }

    private <T> List<String> getColumnList(Class<T> tClass,List<String> fields){
        List<String> result = new ArrayList<>();
        for(String field : fields){
            result.add(ClassUtil.fieldToColumnName(tClass,field));
        }
        return result;
    }
}
