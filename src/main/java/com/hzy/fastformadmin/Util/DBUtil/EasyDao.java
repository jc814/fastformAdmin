package com.hzy.fastformadmin.Util.DBUtil;

import com.hzy.fastformadmin.Util.DBUtil.Util.ClassUtil;
import com.hzy.fastformadmin.Util.DBUtil.base.Command;
import com.hzy.fastformadmin.Util.DBUtil.base.PreCommand;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class EasyDao {
    private PreCommand preCommand;

    public EasyDao() {
        if (preCommand == null) {
            preCommand = new PreCommand();
        }
    }
    private String orderField = "";
    private String orderBy = "";
    private String orderByType = "";
    private String groupBy = "";
    private String limit = "";
    public EasyDao setOrderBy(String orderBy,String type) {
        if(!StringUtils.isEmpty(orderBy)){
            this.orderField = orderBy;
            this.orderByType = type;
        }
        return this;
    }

    public String getOrderBy(String columnName) {
        if(!StringUtils.isEmpty(columnName)){
            return " order by " + columnName + " " + this.orderByType;
        }else {
            return "";
        }
    }

    public String getGroupBy(String columnName) {
        if(!StringUtils.isEmpty(columnName)){
            return "group by "+columnName;
        }else{
            return "";
        }

    }

    public EasyDao setGroupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public String getLimit() {
        return limit;
    }

    public EasyDao setLimit(Integer start,Integer len) {
        if(start == null && len == null){
            this.limit = "";
        }else{
            this.limit = " limit " + start + "," +len;
        }
        return this;
    }

    public <T> Boolean updateById(T t) {
        Command command = preCommand.initUpdate(t);
        Boolean result = command.update();
        return result;
    }

    public <T> Boolean updateByParam(Class<T> tClass, Map<String, Object> columnMap, Map<String, Object> whereMap) {
        Command command = preCommand.initUpdateByParam(tClass, columnMap, whereMap);
        return command.update();
    }

    public <T> Boolean deleteById(Class<T> tClass,String primaryKey) {
        Command command = preCommand.initDelete(tClass,primaryKey);
        return command.update();
    }

    public <T> Boolean deleteByParam(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"delete");
        return command.update();
    }

    public <T> Boolean insert(T t) {
        Command command = preCommand.initInsert(t);
        return command.update();
    }

    public <T> T findObject(Class<T> tClass, String primaryKey) {
        Command command = preCommand.initSelectOne(tClass, primaryKey);
        rebuildSql(tClass,command);
        return command.queryOneObject(tClass);
    }

    public <T> Map<String, Object> findMap(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"select");
        rebuildSql(tClass,command);
        return command.queryOneMap();
    }

    public <T> Map<String, Object> findMap(Class<T> tClass, Map<String, Object> whereMap,String...fields) {
        Command command = preCommand.initFindFieldByParam(tClass, Arrays.asList(fields), whereMap);
        rebuildSql(tClass,command);
        return command.queryOneMap();
    }

    public <T> List<Map<String,Object>> findMapList(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"select");
        rebuildSql(tClass,command);
        return command.queryListMap();
    }

    public <T> List<Map<String,Object>> findMapList(Class<T> tClass,Map<String, Object> whereMap,String...fields) {
        Command command = preCommand.initFindFieldByParam(tClass, Arrays.asList(fields), whereMap);
        rebuildSql(tClass,command);
        return command.queryListMap();
    }


    public <T> List<T> findObjectList(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"select");
        rebuildSql(tClass,command);
        return command.queryListObject(tClass);
    }

    public List<Map<String,Object>> findMySql(String sql, Map<String, Object> whereMap) {
        sql = sql.trim();
        Command command = preCommand.initMySqlByParam(sql, whereMap);
        StringBuffer newSql = new StringBuffer(command.getSQLText()).append(getLimit());
        command.setSQLText(newSql.toString());
        return command.queryListMap();
    }

    public Boolean batchUpdate(String sql, List<Object[]> objects) {
        Command command = preCommand.initBatchUpdate(sql, objects);
        command.setSQLText(sql);
        return command.batchUpdate();
    }

    public Boolean execMySql(String sql, Map<String, Object> whereMap) {
        sql = sql.trim();
        Command command = preCommand.initMySqlByParam(sql, whereMap);
        return command.update();
    }


    private <T> void rebuildSql(Class<T> tClass,Command command){
        StringBuffer sql = new StringBuffer(command.getSQLText());
        sql.append(getGroupBy(ClassUtil.fieldToColumnName(tClass,this.groupBy)))
                .append(getOrderBy(ClassUtil.fieldToColumnName(tClass,this.orderField)))
                .append(getLimit());
        command.setSQLText(sql.toString());
    }
}
