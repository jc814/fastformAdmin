package com.hzy.fastformadmin.Entity;

import lombok.Data;

@Data
public class DesignFieldAdd {
    private String id;
    private String designFieldId;
    private String width;
    private Integer number;
    private String isRequire;
    private String isShow;
    private String checkType;
}
