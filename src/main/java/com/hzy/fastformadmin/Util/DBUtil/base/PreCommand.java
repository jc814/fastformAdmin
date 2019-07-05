package com.hzy.fastformadmin.Util.DBUtil.base;

import com.hzy.fastformadmin.Util.DBUtil.Util.ClassUtil;
import com.hzy.fastformadmin.Util.DBUtil.Util.SQLBuilder;

import java.util.ArrayList;
import java.util.List;

public class PreCommand {
    private SQLBuilder sqlBuilder;
    public PreCommand() {
        if(sqlBuilder == null){
            sqlBuilder = new SQLBuilder();
        }
    }
    public <T> Command initDelete(T entity){
        StringBuilder sql = sqlBuilder.updateBuild(entity.getClass(),"delete");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        List<Object> param = new ArrayList<Object>();
        param.add(ClassUtil.GetkeyFieldValue(entity.getClass()));
        command.setSQLParams(param);
        return command;
    }

    public <T> Command initInsert(T entity){
        StringBuilder sql = sqlBuilder.updateBuild(entity.getClass(),"insert");
        Command command = CommandFactory.getCommand();
        command.setSQLText(sql.toString());
        command.setSQLParams(ClassUtil.EntityValueToArray(entity));
        return command;
    }
}
