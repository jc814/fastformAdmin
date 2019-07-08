package com.hzy.fastformadmin.Util.DBUtil.base;

import com.hzy.fastformadmin.Util.DBUtil.Util.ClassUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Command {
    private JdbcTemplate jdbcTemplate;
    private DriverManagerDataSource dataSource;

    private String SQLText;
    private List<Object> SQLParams = new ArrayList<>();

    public Command() {
        if (jdbcTemplate == null) {
            jdbcTemplate = Config.GetInstance().getJdbcTemplate();
        }
    }

    public Boolean update() {
        int result = 0;
        if (SQLParams.size() > 0) {
            result = jdbcTemplate.update(SQLText, SQLParams);
        } else {
            result = jdbcTemplate.update(SQLText, SQLParams);
        }
        return result > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public <T> T queryOneObject(Class<T> tClass){
        Map<String, Object> objectMap = null;
        if (SQLParams.size() > 0) {
            objectMap = jdbcTemplate.queryForMap(SQLText, SQLParams.toArray());
        } else {
            objectMap = jdbcTemplate.queryForMap(SQLText);
        }
        T object = ClassUtil.MapToObject(tClass, objectMap);
        return object;
    }

    public <T> List<T> queryListObject(Class<T> tClass) {
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

    public Map<String, Object> queryOneMap() {
        Map<String, Object> objectMap = null;
        if (SQLParams.size() > 0) {
            objectMap = jdbcTemplate.queryForMap(SQLText, SQLParams.toArray());
        } else {
            objectMap = jdbcTemplate.queryForMap(SQLText);
        }
        return objectMap;

    }

    public List<Map<String, Object>> queryListMap() {
        List<Map<String, Object>> objectList = new ArrayList<>();
        if (SQLParams.size() > 0) {
            objectList = jdbcTemplate.queryForList(SQLText, SQLParams.toArray());
        } else {
            objectList = jdbcTemplate.queryForList(SQLText);
        }
        return objectList;
    }
}
