package com.hzy.fastformadmin.Util.DBUtil.base;


import org.springframework.cglib.proxy.Enhancer;

public class CommandFactory {
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
