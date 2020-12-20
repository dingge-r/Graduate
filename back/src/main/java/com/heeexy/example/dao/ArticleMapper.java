package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Article;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    @Select("SELECT id, content, DATE_FORMAT(create_time, '%Y.%m.%d %T') createTime\n" +
            "FROM article WHERE delete_status = '1' ORDER BY id  DESC")
    List<JSONObject> list();

}
