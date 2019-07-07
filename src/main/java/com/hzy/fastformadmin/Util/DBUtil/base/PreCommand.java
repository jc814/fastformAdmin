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
    public <T> Command initDelete(T entity){
        StringBuilder sql = sqlBuilder.baseSqlBuild(entity.getClass(),"delete");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        List<Object> param = new ArrayList<Object>();
        param.add(ClassUtil.GetkeyFieldValue(entity.getClass()));
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
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Ignore.class) || field.isAnnotationPresent(Key.class)) {
                    param.add(field.get(entity));
                }
            }
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

    public <T> Command initUpdOrFindMapByParam(String tableName,Map<String,Object> columnMap,Map<String,Object> whereMap,String type){
        List<String> columnKeys ;
        List<Object> columnValues ;
        List<String> whereKeys ;
        List<Object> whereValues ;
        StringBuffer sql = new StringBuffer();
        columnKeys = (List<String>)mapToList(columnMap).get("key");
        columnValues = (List<Object>)mapToList(columnMap).get("values");
        whereKeys = (List<String>)mapToList(whereMap).get("key");
        whereValues = (List<Object>)mapToList(whereMap).get("values");
        if("select".equals(type)){
            sql = sqlBuilder.selectParamSqlBuild(columnKeys,whereKeys,tableName,"param");
        }else{
            sql = sqlBuilder.updateParamSqlBuild(columnKeys,whereKeys,tableName);
        }
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        columnValues.addAll(whereValues);
        command.setSQLParams(columnValues);
        return command;
    }

    public <T> Command initDelOrFindObjByParam(String tableName,Map<String,Object> whereMap){
        List<String> whereKeys ;
        List<Object> whereValues ;
        whereKeys = (List<String>)mapToList(whereMap).get("key");
        whereValues = (List<Object>)mapToList(whereMap).get("values");
        StringBuffer sql = new StringBuffer();
        if("select".equals("type")){
            sql = sqlBuilder.deleteParamSqlBuild(whereKeys,tableName);
        }else{
            sql = sqlBuilder.selectParamSqlBuild(null,whereKeys,tableName,"all");
        }
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(whereValues);
        return command;
    }

    private Map<String,Object> mapToList(Map<String,Object> map){
        Map<String,Object> result = new HashMap<>();
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry columnEntry = (Map.Entry)entries.next();
            keys.add(columnEntry.getKey().toString());
            values.add(columnEntry.getValue());
        }
        result.put("keys",keys);
        result.put("values",values);
        return result;
    }
}
