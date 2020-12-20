package com.heeexy.example.util.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(description = "用户类")
@Table(name = "student")
@Data
public class Student {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String sname;
    private Integer sex; // 1 女 2 男
    private String phone;
    private String studentnumber;
    private String academy;
    private String classes;

}
