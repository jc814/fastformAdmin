package com.hzy.fastformadmin.Entity;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("design_field")
public class DesignField {
    @ColumnName("ID")
    private String id;
    @ColumnName("DESIGN_ID")
    private String designId;
    @ColumnName("LABEL_NAME")
    private String labelName;
    @ColumnName("FIELD_NAME")
    private String fieldName;
    @ColumnName("FIELD_REF")
    private String fieldRef;
    @ColumnName("IS_DISPLAY")
    private String isDisplay;


}
