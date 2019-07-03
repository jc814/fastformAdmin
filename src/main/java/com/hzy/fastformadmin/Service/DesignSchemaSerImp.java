package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Mapper.DesignSchemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesignSchemaSerImp implements DesignSchemaSer{
    @Autowired
    private DesignSchemaMapper schemaMapper;

    @Override
    public DesignSchema findOne(String id) {
        return schemaMapper.findOne(id);
    }
}
