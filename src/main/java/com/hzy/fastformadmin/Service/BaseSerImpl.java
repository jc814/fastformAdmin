package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseSerImpl<T> implements BaseSer<T> {
    @Autowired
    public EasyDao easyDao;

    @Override
    public Boolean insert(T t) {
        return easyDao.insert(t);
    }

    @Override
    public Boolean updateById(T t) {
        return easyDao.updateById(t);
    }

    @Override
    public Boolean updateByParam(Class<T> tClass, Map<String, Object> columnMap, Map<String, Object> whereMap){
        return easyDao.updateByParam(tClass,columnMap,whereMap);
    }

    @Override
    public Boolean deleteById(Class<T> tClass, String primaryKey) {
        return easyDao.deleteById(tClass, primaryKey);
    }

    @Override
    public Boolean deleteByParam(Class<T> tClass, Map<String, Object> whereMap) {
        return easyDao.deleteByParam(tClass, whereMap);
    }

    @Override
    public T findObject(Class<T> tClass, String primaryKey) {
        return (T) easyDao.findObject(tClass, primaryKey);
    }

    @Override
    public List<T> findObjectList(Class<T> tClass, Map<String, Object> map) {
        return (List<T>) easyDao.findObjectList(tClass, map);
    }

    @Override
    public Map<String, Object> findMap(Class<T> tClass, Map<String, Object> map) {
        return easyDao.findMap(tClass, map);
    }

    @Override
    public List<Map<String, Object>> findMapList(Class<T> tClass, Map<String, Object> map) {
        return easyDao.findMapList(tClass, map);
    }

    @Override
    public Map<String, Object> findMap(Class<T> tClass, Map<String, Object> whereMap, String... fields) {
        return easyDao.findMap(tClass, whereMap, fields);
    }

    @Override
    public List<Map<String, Object>> findMapList(Class<T> tClass, Map<String, Object> whereMap, String... fields) {
        return easyDao.findMapList(tClass, whereMap, fields);
    }

}
