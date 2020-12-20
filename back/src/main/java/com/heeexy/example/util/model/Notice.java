package com.heeexy.example.util.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@ApiModel(description = "公告类")
@Table(name = "notice")
@Data
public class Notice {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String nname;
    private Timestamp createtime;
    private String content;

}
