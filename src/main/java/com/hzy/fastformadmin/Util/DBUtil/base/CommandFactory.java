package com.hzy.fastformadmin.Util.DBUtil.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;

import javax.sql.DataSource;

public class CommandFactory {
    @Autowired
    DataSource dataSource;
    public static Command getCommand(){
        CommandProxy commandProxy = new CommandProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Command.class);
        enhancer.setCallback(commandProxy);
        Command command = (Command) enhancer.create();
        command.setJdbcTemplate(Config.GetInstance().getJdbcTemplate());
        return command;
    }
}
