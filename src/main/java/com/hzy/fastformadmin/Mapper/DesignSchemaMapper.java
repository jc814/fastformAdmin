package com.hzy.fastformadmin.Mapper;


import com.hzy.fastformadmin.Entity.DesignSchema;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignSchemaMapper {

    @Select("select * from `design_schema` where ID = #{id}")
    DesignSchema findOne(@Param("id") String id);

}
