package com.hzy.fastformadmin.Controller;

import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Service.DesignSchemaSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/schema")
public class SchemaWeb{
    @Autowired
    private DesignSchemaSer designSchemaSer;

    @RequestMapping(value="/fineOne/{id}")
    public DesignSchema findOne(@PathVariable("id")  String id) {
        return designSchemaSer.findOne(id);
    }
}
