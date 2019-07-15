package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.DesignField;
import com.hzy.fastformadmin.Util.MapUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DesignFieldSerImp extends BaseSerImpl<DesignField> implements DesignFieldSer {


    @Override
    public List<Map<String, Object>> fieldList(String designId) {
        String sql = "select " +
                "f.ID as id," +
                "f.LABEL_NAME as labelName," +
                "f.FIELD_NAME as fieldName," +
                "f.FIELD_REF as fieldRef," +
                "s.IS_SHOW as searchShow," +
                "l.IS_SHOW as listShow," +
                "a.IS_SHOW as addShow " +
                "from design_field f " +
                "left join design_field_add a on f.ID = a.DESIGN_FIELD_ID " +
                "left join design_field_list l on f.ID = l.DESIGN_FIELD_ID " +
                "left join design_field_search s on f.ID = s.DESIGN_FIELD_ID " +
                "where " +
                "f.DESIGN_ID = #designId";
        return easyDao.findMySql(sql, MapUtil.newMap("designId",designId));
    }

    @Override
    public Boolean changeShow(String fieldId, String type, String state) {
        String sql ="";
        switch (type){
            case "add":
                sql = "update design_field_add set IS_SHOW = #state where DESIGN_FIELD_ID = #fieldId";
                break;
            case "list":
                sql = "update design_field_list set IS_SHOW = #state where DESIGN_FIELD_ID = #fieldId";
                break;
            case "search":
                sql = "update design_field_search set IS_SHOW = #state where DESIGN_FIELD_ID = #fieldId";
                break;
        }
        return easyDao.execMySql(sql,MapUtil.newMap("state",state,"fieldId",fieldId));
    }
}
