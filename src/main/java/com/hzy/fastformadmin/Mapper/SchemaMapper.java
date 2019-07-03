package com.hzy.fastformadmin.Mapper;


import com.hzy.fastformadmin.Entity.Schema;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemaMapper {

    @Select("select * from `schema` where ID = #{id}")
    Schema findOne(@Param("id") String id);

}
