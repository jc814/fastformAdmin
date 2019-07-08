package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Mapper.DesignSchemaMapper;
import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DesignSchemaSerImp implements DesignSchemaSer{
    @Autowired
    private DesignSchemaMapper schemaMapper;

    @Autowired
    private EasyDao easyDao;

    @Override
    public DesignSchema findOne(String id) {

        return easyDao.findObject(DesignSchema.class,"1");
    }
}
