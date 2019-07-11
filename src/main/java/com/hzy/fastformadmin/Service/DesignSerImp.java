package com.hzy.fastformadmin.Service;

import com.hzy.fastformadmin.Entity.Design;
import com.hzy.fastformadmin.Entity.DesignField;
import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import com.hzy.fastformadmin.Util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DesignSerImp extends BaseSerImpl<Design> implements DesignSer {

    @Autowired
    private EasyDao easyDao;

    @Override
    public Boolean designAdd(Design design) {
        easyDao.insert(design);
        List<DesignField> fields = easyDao.findObjectList(DesignField.class, MapUtil.newMap("designId",design.getId()));

    }
}
