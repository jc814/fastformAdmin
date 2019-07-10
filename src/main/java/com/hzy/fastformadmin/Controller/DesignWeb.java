package com.hzy.fastformadmin.Controller;

import com.hzy.fastformadmin.Entity.Design;
import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Service.DesignSchemaSer;
import com.hzy.fastformadmin.Service.DesignSer;
import com.hzy.fastformadmin.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/design")
public class DesignWeb {

    @Autowired
    public DesignSer designSer;

    @RequestMapping(value="/fineAll")
    public List<Design> fineAll() {
        return designSer.findObjectList(Design.class,null);
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public Boolean insert(@RequestBody Design design) {
        design.setId(StringUtil.createUUID());
        return designSer.insert(design);
    }
}
