package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Recruit;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RecruitDao extends Mapper<Recruit> {
    @Select("SELECT * FROM recruit WHERE '1' = '1' ORDER BY id  DESC")
    List<JSONObject> list();
}
