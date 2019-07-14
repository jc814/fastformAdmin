package com.hzy.fastformadmin.Util.DBUtil.base;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ResourceBundle;

@Data
public class Config {
    @Value("${spring.datasource.url:#{null}}")
    public String url;
    @Value("${spring.datasource.username:#{null}}")
    public String username;
    @Value("${spring.datasource.password:#{null}}")
    public String password;
    @Value("${spring.datasource.driverClassName:#{null}}")
    public String driverClassName;

    @Resource
    DataSource dataSource;

    private static Config config;

    public Config() {
        ResourceBundle resource = ResourceBundle.getBundle("application");
        url = resource.getString("spring.datasource.url");
        username = resource.getString("spring.datasource.username");
        password = resource.getString("spring.datasource.password");
        driverClassName = resource.getString("spring.datasource.driverClassName");
    }

    public static Config GetInstance() {
        if (null == config) {
            config = new Config();
        }
        return config;
    }

    public JdbcTemplate getJdbcTemplate() {
        //return new JdbcTemplate(GetInstance().getDataSource());
        return new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate(DriverManagerDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getDriverClassName());
        dataSource.setUrl(getUrl());
        dataSource.setUsername(getUsername());
        dataSource.setPassword(getPassword());
        return dataSource;
    }

    public DriverManagerDataSource getDataSource(String url, String username, String password, String driverClassName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

}
