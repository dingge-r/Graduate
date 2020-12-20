package com.heeexy.example.util.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(description = "招聘信息类")
@Table(name = "recruit")
@Data
public class Recruit {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String position;  //职位
    private String pay;     //薪资
    private String welfare;  //福利
    private  String request;  //职位要求
    private String qualification;  //学历要求
    private String experience;    //工作经验
    private String companyname;   //公司名字
    private String address;      //公司地址
    private String companyintro;  //公司介绍
}
