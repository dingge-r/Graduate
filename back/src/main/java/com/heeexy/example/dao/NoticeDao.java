package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Notice;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NoticeDao extends Mapper<Notice> {
    @Select("SELECT id,nname, content, DATE_FORMAT(createtime, '%Y.%m.%d %T') createtime\n" +
            "FROM notice WHERE '1' = '1' ORDER BY id  DESC")
    List<JSONObject> list();
}
