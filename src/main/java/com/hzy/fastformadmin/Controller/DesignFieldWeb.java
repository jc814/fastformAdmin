package com.hzy.fastformadmin.Controller;

import com.hzy.fastformadmin.Entity.Design;
import com.hzy.fastformadmin.Entity.DesignField;
import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Service.DesignFieldSer;
import com.hzy.fastformadmin.Service.DesignSchemaSer;
import com.hzy.fastformadmin.Util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/field")
public class DesignFieldWeb {

    @Autowired
    public DesignFieldSer designFieldSer;

    @RequestMapping(value="/getList")
    public List<DesignField> fineAll(String designId) {
        return designFieldSer.findObjectList(DesignField.class, MapUtil.newMap("designId",designId));
    }

}
