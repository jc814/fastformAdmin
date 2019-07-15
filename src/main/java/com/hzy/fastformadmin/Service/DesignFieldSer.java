package com.hzy.fastformadmin.Service;



import com.hzy.fastformadmin.Entity.DesignField;

import java.util.List;
import java.util.Map;


public interface DesignFieldSer extends BaseSer<DesignField> {

    public List<Map<String,Object>> fieldList(String designId);

    public Boolean changeShow(String fieldId,String type,String state);

}
