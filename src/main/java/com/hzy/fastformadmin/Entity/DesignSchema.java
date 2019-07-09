package com.hzy.fastformadmin.Entity;

import com.hzy.fastformadmin.Util.DBUtil.annotation.ColumnName;
import com.hzy.fastformadmin.Util.DBUtil.annotation.Key;
import com.hzy.fastformadmin.Util.DBUtil.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
@Data
@Accessors(chain = true)
@TableName("design_schema")
public class DesignSchema {
    @Key
    @ColumnName("ID")
    private String id;
    @ColumnName("SCHEMA_NAME")
    private String schemaName;
    @ColumnName("DESIGN_ID")
    private String designId;
    @ColumnName("DESCRIPTION")
    private String description;

}
