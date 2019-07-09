package com.hzy.fastformadmin.Controller;

import com.hzy.fastformadmin.Service.TableSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/table")
public class TableWeb {

    @Autowired
    public TableSer tableSer;

    @RequestMapping(value="/getTables")
    public List<String> getTables() {
        return tableSer.getTableName();
    }
    @RequestMapping(value="/getFields")
    public List<String> getTables(String tableName) {
        return tableSer.getTableFields(tableName);
    }
}
