package com.hzy.fastformadmin.Util.DBUtil.base;

import com.hzy.fastformadmin.Util.DBUtil.Util.ClassUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Command {
    private JdbcTemplate jdbcTemplate;
    private DriverManagerDataSource dataSource;

    private String SQLText;
    private List<Object> SQLParams;

    public Command() {
        if (jdbcTemplate == null) {
            jdbcTemplate = Config.GetInstance().getJdbcTemplate(dataSource);
        }
    }

    private Boolean update() {
        int result = 0;
        if (SQLParams.size() > 0) {
            result = jdbcTemplate.update(SQLText, SQLParams);
        } else {
            result = jdbcTemplate.update(SQLText, SQLParams);
        }
        return result > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    private <T> T queryOneObject(Class<T> tClass) throws Exception {
        Map<String, Object> objectMap = null;
        if (SQLParams.size() > 0) {
            objectMap = jdbcTemplate.queryForMap(SQLText, SQLParams.toArray());
        } else {
            objectMap = jdbcTemplate.queryForMap(SQLText);
        }
        T object = ClassUtil.MapToObject(tClass, objectMap);
        return object;
    }

    private <T> List<T> queryListObject(Class<T> tClass) throws Exception {
        T obj = tClass.newInstance();
        List<Map<String, Object>> objectList = new ArrayList<>();
        List<T> result = new ArrayList<>();
        if (SQLParams.size() > 0) {
            objectList = jdbcTemplate.queryForList(SQLText, SQLParams.toArray());
        } else {
            objectList = jdbcTemplate.queryForList(SQLText);
        }
        for(Map<String, Object> map : objectList){
            result.add(ClassUtil.MapToObject(tClass, map));
        }
        return result;
    }

    private Map<String, Object> queryOneMap() {
        Map<String, Object> objectMap = null;
        if (SQLParams.size() > 0) {
            objectMap = jdbcTemplate.queryForMap(SQLText, SQLParams.toArray());
        } else {
            objectMap = jdbcTemplate.queryForMap(SQLText);
        }
        return objectMap;

    }

    private List<Map<String, Object>> queryListMap() {
        List<Map<String, Object>> objectList = new ArrayList<>();
        if (SQLParams.size() > 0) {
            objectList = jdbcTemplate.queryForList(SQLText, SQLParams.toArray());
        } else {
            objectList = jdbcTemplate.queryForList(SQLText);
        }
        return objectList;
    }
}
