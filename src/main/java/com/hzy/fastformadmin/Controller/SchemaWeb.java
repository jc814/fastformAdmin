package com.hzy.fastformadmin.Controller;

import com.hzy.fastformadmin.Entity.Schema;
import com.hzy.fastformadmin.Mapper.SchemaMapper;
import com.hzy.fastformadmin.Service.SchemaSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/schema")
public class SchemaWeb{
    @Autowired
    private SchemaSer schemaSer;

    @RequestMapping(value="/fineOne/{id}")
    public Schema findOne(@PathVariable("id")  String id) {
        return schemaSer.findOne(id);
    }
}
