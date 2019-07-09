package com.hzy.fastformadmin.Entity;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("design")
public class Design {
    @Key
    @ColumnName("ID")
    private String id;
    @ColumnName("DESIGN_NAME")
    private String designName;
    @ColumnName("IS_SINGLE")
    private String isSingle;
    @ColumnName("ORDER_BY")
    private String orderBy;
    @ColumnName("PRIMARY_KEY")
    private String primaryKey;
    @ColumnName("BUTTONS")
    private String buttons;
    @ColumnName("COLUMN_NUM")
    private String columnNum;
    @ColumnName("UNION_SQL")
    private String unionSql;
    @ColumnName("TABLE_NAME")
    private String tableName;
    @ColumnName("DESCRIPTION")
    private String description;


}
