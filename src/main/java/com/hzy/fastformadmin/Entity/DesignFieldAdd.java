package com.hzy.fastformadmin.Entity;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("design_field_add")
public class DesignFieldAdd {
    @Key
    @ColumnName("ID")
    private String id;
    @ColumnName("DESIGN_FIELD_ID")
    private String designFieldId;
    @ColumnName("WIDTH")
    private String width;
    @ColumnName("NUMBER")
    private Integer number;
    @ColumnName("IS_REQUIRE")
    private String isRequire;
    @ColumnName("IS_SHOW")
    private String isShow;
    @ColumnName("CHECK_TYPE")
    private String checkType;


}
