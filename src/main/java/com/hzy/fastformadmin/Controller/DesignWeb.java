package com.hzy.fastformadmin.Controller;

import com.hzy.fastformadmin.Entity.Design;
import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Service.DesignSchemaSer;
import com.hzy.fastformadmin.Service.DesignSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/design")
public class DesignWeb {

    @Autowired
    public DesignSer designSer;

    @RequestMapping(value="/fineAll")
    public List<Design> fineAll() {
        return designSer.findObjectList(Design.class,null);
    }
}
