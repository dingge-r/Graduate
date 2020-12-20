package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentDao extends Mapper<Student> {
    @Select("SELECT id, sname,sex*1 as sex,phone,studentnumber,academy,classes,resume FROM student WHERE '1' = '1' ORDER BY id  DESC")
    List<JSONObject> list();

    @Update("UPDATE student set resume=#{resume} WHERE id=#{id};")
    boolean updateResume(@Param("resume") String resume, @Param("id") Integer id);
}
