package com.hzy.fastformadmin.Util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;

public class SqlUtil {

    @Bean
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDataSource());
        factoryBean.setVfs(SpringBootVFS.class); // Sets the SpringBootVFS class into SqlSessionFactoryBean
        // ...
        return factoryBean.getObject();
    }
}
