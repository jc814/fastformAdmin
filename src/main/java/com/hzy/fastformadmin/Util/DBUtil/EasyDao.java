package com.hzy.fastformadmin.Util.DBUtil;

import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import com.hzy.fastformadmin.Util.DBUtil.base.Command;
import com.hzy.fastformadmin.Util.DBUtil.base.PreCommand;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
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
        return command.queryOneObject(tClass);
    }

    public <T> Map<String, Object> findMap(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"select");
        return command.queryOneMap();
    }

    public <T> Map<String, Object> findMap(Class<T> tClass, Map<String, Object> whereMap,String...fields) {
        Command command = preCommand.initFindFieldByParam(tClass, Arrays.asList(fields), whereMap);
        return command.queryOneMap();
    }

    public <T> List<Map<String,Object>> findMapList(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"select");
        return command.queryListMap();
    }

    public <T> List<Map<String,Object>> findMapList(Class<T> tClass,Map<String, Object> whereMap,String...fields) {
        Command command = preCommand.initFindFieldByParam(tClass, Arrays.asList(fields), whereMap);
        return command.queryListMap();
    }


    public <T> List<T> findObjectList(Class<T> tClass, Map<String, Object> whereMap) {
        Command command = preCommand.initDelOrFindAllByParam(tClass, whereMap,"select");
        return command.queryListObject(tClass);
    }

    public List<Map<String,Object>> findMySql(String sql, Map<String, Object> whereMap) {
        sql = sql.trim();
        Command command = preCommand.initMySqlByParam(sql, whereMap);
        return command.queryListMap();
    }

    public Boolean execMySql(String sql, Map<String, Object> whereMap) {
        sql = sql.trim();
        Command command = preCommand.initMySqlByParam(sql, whereMap);
        return command.update();
    }
}
