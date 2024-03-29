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

    @RequestMapping(value="/getList")
    public List<Design> fineAll() {
        return designSer.findObjectList(Design.class,null);
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public Boolean insert(@RequestBody Design design) {
        if(StringUtil.isNotEmpty(design.getId())){
            return designSer.updateById(design);
        }else{
            return designSer.designAdd(design);
        }

    }

    @RequestMapping(value="/delete/{designId}")
    public Boolean designDel(@PathVariable ( "designId" ) String designId) {
        return designSer.designDel(designId);
    }

    @RequestMapping(value="/designView/{designId}")
    public Design designView(@PathVariable ( "designId" ) String designId) {
        return designSer.findObject(Design.class, designId);
    }
}
