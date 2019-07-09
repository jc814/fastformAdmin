package com.hzy.fastformadmin.Service;


import java.util.List;

public interface TableSer {
    public List<String> getTableName();
    public List<String> getTableFields(String tableName);
}
