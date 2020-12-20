package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Recruit;
import com.heeexy.example.util.model.Student;
import org.springframework.web.multipart.MultipartFile;

public interface RecruitService {

    Boolean addRecruit(Recruit recruit);

    JSONObject listRecruit(int page, int size);

    Boolean updateRecruit(Recruit recruit);

    boolean delete(Integer id);

}
