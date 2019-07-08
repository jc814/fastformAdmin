package com.hzy.fastformadmin;

import com.hzy.fastformadmin.Entity.DesignSchema;
import com.hzy.fastformadmin.Util.DBUtil.EasyDao;
import com.hzy.fastformadmin.Util.MapUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastformadminApplicationTests {

    @Autowired
    private EasyDao easyDao;

    @Test
    public void updateById() {
        DesignSchema t = new DesignSchema();
        t.setId("1").setSchemaName("2").setDesignId("2").setDescribe("2");
        easyDao.updateById(t);
    }

    @Test
    public void updateByParam() {
        Map<String, Object> map1 = MapUtil.newMap("describe","3","designId","3");
        Map<String, Object> map2 = MapUtil.newMap("id","1","schemaName","2");
        easyDao.updateByParam(DesignSchema.class,map1,map2);
    }

    @Test
    public void deleteById() {
        easyDao.deleteById(DesignSchema.class,"1");
    }

    @Test
    public void deleteByParam() {
        Map<String, Object> map1 = MapUtil.newMap("describe","2","designId","2");
        easyDao.deleteByParam(DesignSchema.class,map1);
    }

    @Test
    public void insert() {
        DesignSchema t1 = new DesignSchema();
        t1.setId("1").setSchemaName("2").setDesignId("2").setDescribe("2");
        DesignSchema t2 = new DesignSchema();
        t2.setId("2").setSchemaName("3").setDesignId("3").setDescribe("3");
        DesignSchema t3 = new DesignSchema();
        t3.setId("3").setSchemaName("3").setDesignId("4").setDescribe("4");
        easyDao.insert(t1);
        easyDao.insert(t2);
        easyDao.insert(t3);
    }

    @Test
    public void findObject() {
        easyDao.findObject(DesignSchema.class,"1");
    }

    @Test
    public void findMap1() {
        Map<String, Object> map1 = MapUtil.newMap("id","1","schemaName","2");
        easyDao.findMap(DesignSchema.class,map1);
    }

    @Test
    public void findMap2() {
        Map<String, Object> map1 = MapUtil.newMap("id","1","schemaName","2");
        easyDao.findMap(DesignSchema.class,map1,"describe","designId");
    }

    @Test
    public void findMapList1() {
        Map<String, Object> map1 = MapUtil.newMap("schemaName","3");
        easyDao.findMapList(DesignSchema.class,map1);
    }

    @Test
    public void findMapList2() {
        Map<String, Object> map1 = MapUtil.newMap("schemaName","3");
        easyDao.findMapList(DesignSchema.class,map1,"describe","designId");
    }

    @Test
    public void findObjectList() {
        Map<String, Object> map1 = MapUtil.newMap("schemaName","3");
        easyDao.findObjectList(DesignSchema.class,map1);
    }

    @Test
    public void findMySql() {
        Map<String, Object> map1 = MapUtil.newMap("b","1","a","2");
        String sql = "select from design_schema ds left join design d on ds.DESIGN_ID = d.ID where ds.SCHEMA_NAME = #a and ds.ID = #b";
        easyDao.findMySql(sql,map1);
    }

    @Test
    public void execMySql() {
        String sql = "update design_schema set DESCRIBE = #a,DESIGN_ID = #b where ID = #c";
        Map<String, Object> map1 = MapUtil.newMap("a","2","b","2","c","2");
        easyDao.execMySql(sql,map1);
    }

}
