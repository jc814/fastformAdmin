package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.Design;
import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import com.hzy.fastformadmin.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DesignSerImp extends BaseSerImpl<Design> implements DesignSer {

    @Autowired
    private EasyDao easyDao;

    @Autowired
    private TableSer tableSer;

    @Override
    public Boolean designAdd(Design design) {
        List<Object[]> objects = new ArrayList<>();
        design.setId(StringUtil.createUUID());
        easyDao.insert(design);
        List<String> fields = tableSer.getTableFields(design.getTableName());
        String sql = "insert into design_field (ID,DESIGN_ID,FIELD_NAME,FIELD_REF,IS_DISPLAY) VALUES (?,?,?,?,?)";
        fields.forEach(str->{
            Object[] objs= {StringUtil.createUUID(),design.getId(),str,str,1};
            objects.add(objs);
        });
        return easyDao.batchUpdate(sql,objects);
    }
}
