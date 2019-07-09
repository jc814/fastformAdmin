package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import com.hzy.fastformadmin.Util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class TableSerImp implements TableSer {

    @Autowired
    private EasyDao easyDao;

    @Value("${dataBaseName}")
    private String dataBaseName;
    @Override
    public List<String> getTableName() {
        List<Map<String,Object>> list = easyDao.findMySql("select table_name from information_schema.tables where table_schema = #dataBaseName and table_name not like 'design%'",
                MapUtil.newMap("dataBaseName",dataBaseName));
        return MapUtil.getValues(list,"table_name");
    }

    @Override
    public List<String> getTableFields(String tableName) {
        List<Map<String,Object>> list = easyDao.findMySql("select column_name from information_schema.columns where table_schema = #dataBaseName and table_name = #tableName",
                MapUtil.newMap("dataBaseName",dataBaseName,"tableName",tableName));
        return MapUtil.getValues(list,"column_name");
    }
}
