package com.hzy.fastformadmin.Util.DBUtil.base;

import com.hzy.fastformadmin.Util.DBUtil.Util.SQLBuilder;

public class PreCommand {
    private SQLBuilder builder;
    public PreCommand() {
        if(builder == null){
            builder = new SQLBuilder();
        }
    }
    public <T> Command initdelete(T entity){
        StringBuilder sql = builder.updateBuild(entity.getClass(),"delete");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());

    }
}
