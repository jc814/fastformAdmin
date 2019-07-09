package com.hzy.fastformadmin.Entity;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("design_field_list")
public class DesignFieldList {
    @Key
    @ColumnName("ID")
    private String id;
    @ColumnName("DESIGN_FIELD_ID")
    private String designFieldId;
    @ColumnName("WIDTH")
    private String width;
    @ColumnName("NUMBER")
    private Integer number;
    @ColumnName("IS_SHOW")
    private String isShow;


}
