package com.heeexy.example.util.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@ApiModel(description = "用户类")
@Table(name = "article")
@Data
public class Article {

    @Id
    @KeySql(useGeneratedKeys = true)
   private Integer id;
   private String content;
   private Timestamp createTime;
   private Timestamp  updateTime;
   private String delete_status;
}
