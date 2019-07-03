package com.hzy.fastformadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hzy.fastformadmin.Mapper")
public class FastformadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastformadminApplication.class, args);
    }

}
