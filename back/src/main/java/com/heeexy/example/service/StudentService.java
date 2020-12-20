package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Student;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    /**
     * 新增学生
     */
    Boolean addStudent(Student student);

    /**
     * 学生列表
     */
    JSONObject listStudent(int page, int size);

    /**
     * 更新学生
     */
    Boolean updateStudent(Student student);

    boolean delete(Integer id);

    JSONObject uploadWord(MultipartFile file,Integer id);
}
