package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.*;
import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import com.hzy.fastformadmin.Util.MapUtil;
import com.hzy.fastformadmin.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DesignSerImp extends BaseSerImpl<Design> implements DesignSer {

    @Autowired
    private EasyDao easyDao;

    @Autowired
    private TableSer tableSer;

    @Value("${dataBaseName}")
    private String dataBaseName;

    @Override
    public Boolean designAdd(Design design) {
        List<Object[]> fieldObjects = new ArrayList<>();
        List<Object[]> fieldAddObjects = new ArrayList<>();
        List<Object[]> fieldListObjects = new ArrayList<>();
        List<Object[]> fieldSearchObjects = new ArrayList<>();
        design.setId(StringUtil.createUUID());
        easyDao.insert(design);
        List<String> fields = tableSer.getTableFields(design.getTableName());
        String fieldSql = "insert into design_field (ID,DESIGN_ID,FIELD_NAME,FIELD_REF,IS_DISPLAY,LABEL_NAME) VALUES (?,?,?,?,?,?)";
        String fieldAddSql = "insert into design_field_add (ID,DESIGN_FIELD_ID,WIDTH,NUMBER,IS_REQUIRE,IS_SHOW,CHECK_TYPE) VALUES (?,?,?,?,?,?,?)";
        String fieldSearchSql = "insert into design_field_search (ID,DESIGN_FIELD_ID,WIDTH,ONELINE,NUMBER,IS_SHOW) VALUES (?,?,?,?,?,?)";
        String fieldListSql = "insert into design_field_list (ID,DESIGN_FIELD_ID,WIDTH,NUMBER,IS_SHOW) VALUES (?,?,?,?,?)";
        fields.forEach(str->{
            List<Map<String,Object>> list = easyDao.findMySql("SELECT COLUMN_COMMENT FROM information_schema.COLUMNS WHERE table_schema = #database and table_name = #tableName and COLUMN_NAME = #fieldName",
                    MapUtil.newMap("database",dataBaseName,"tableName",design.getTableName(),"fieldName",str));
            String fieldId = StringUtil.createUUID();
            Object[] fieldObject = {fieldId,design.getId(),str,str,1,MapUtil.getValue(list.get(0),"COLUMN_COMMENT")};
            Object[] fieldAddObject = {StringUtil.createUUID(),fieldId,100,0,0,0,1};
            Object[] fieldListObject = {StringUtil.createUUID(),fieldId,100,0,0,0};
            Object[] fieldSearchObject = {StringUtil.createUUID(),fieldId,100,0,0};
            fieldObjects.add(fieldObject);
            fieldAddObjects.add(fieldAddObject);
            fieldListObjects.add(fieldListObject);
            fieldSearchObjects.add(fieldSearchObject);
        });
        easyDao.batchUpdate(fieldSql,fieldObjects);
        easyDao.batchUpdate(fieldAddSql,fieldAddObjects);
        easyDao.batchUpdate(fieldSearchSql,fieldListObjects);
        easyDao.batchUpdate(fieldListSql,fieldSearchObjects);
        return Boolean.TRUE;
    }

    @Override
    public Boolean designDel(String designId) {
        easyDao.deleteById(Design.class,designId);
        List<DesignField> list = easyDao.findObjectList(DesignField.class,MapUtil.newMap("designId",designId));
        list.forEach(field -> {
            easyDao.deleteByParam(DesignFieldAdd.class,MapUtil.newMap("designFieldId",field.getId()));
            easyDao.deleteByParam(DesignFieldList.class,MapUtil.newMap("designFieldId",field.getId()));
            easyDao.deleteByParam(DesignFieldSearch.class,MapUtil.newMap("designFieldId",field.getId()));
        });
        easyDao.deleteByParam(DesignField.class,MapUtil.newMap("designId",designId));
        return Boolean.TRUE;
    }
}
