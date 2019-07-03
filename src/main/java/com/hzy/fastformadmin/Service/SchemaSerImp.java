package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.Schema;
import com.hzy.fastformadmin.Mapper.SchemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchemaSerImp implements SchemaSer{
    @Autowired
    private SchemaMapper schemaMapper;

    @Override
    public Schema findOne(String id) {
        return schemaMapper.findOne(id);
    }
}
