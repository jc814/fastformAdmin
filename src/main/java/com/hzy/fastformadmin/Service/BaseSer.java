package com.hzy.fastformadmin.Service;


import com.hzy.fastformadmin.Util.DBUtil.base.Command;

import java.util.List;
import java.util.Map;

public interface BaseSer<T> {
    public Boolean insert(T t);

    public Boolean updateById(T t);

    public Boolean updateByParam(Class<T> tClass, Map<String, Object> columnMap, Map<String, Object> whereMap);

    public Boolean deleteById(Class<T> tClass, String primaryKey);

    public Boolean deleteByParam(Class<T> tClass, Map<String, Object> whereMap);

    public T findObject(Class<T> tClass, String primaryKey);

    public List<T> findObjectList(Class<T> tClass, Map<String, Object> map);

    public Map<String, Object> findMap(Class<T> tClass, Map<String, Object> map);

    public List<Map<String, Object>> findMapList(Class<T> tClass, Map<String, Object> map);

    public Map<String, Object> findMap(Class<T> tClass, Map<String, Object> whereMap, String... fields);

    public List<Map<String, Object>> findMapList(Class<T> tClass, Map<String, Object> whereMap, String... fields);
}
