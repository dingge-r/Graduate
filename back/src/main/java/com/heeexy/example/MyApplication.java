package com.heeexy.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Summer
 * @description: SpringBoot启动类
 * @date: 2020/11/22 11:09
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.heeexy.example.dao")
public class MyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}
