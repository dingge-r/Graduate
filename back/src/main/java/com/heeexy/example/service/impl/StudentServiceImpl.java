package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heeexy.example.dao.StudentDao;
import com.heeexy.example.service.StudentService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.heeexy.example.util.constants.ErrorEnum.E_400;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Boolean addStudent(Student student) {
        int i = studentDao.insertSelective(student);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public JSONObject listStudent(int page, int size) {
        PageHelper.startPage(page,size);
        List<JSONObject> user=studentDao.list();
        PageInfo<JSONObject> list = new PageInfo<>(user);
        return  CommonUtil.successPage(list.getPageSize(), list.getList(), list.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStudent(Student student) {
        int i = studentDao.updateByPrimaryKeySelective(student);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        int i = studentDao.deleteByPrimaryKey(id);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public JSONObject uploadWord(MultipartFile file,Integer id) {
        try {
            Map result = new HashMap();
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            fileName = uuid + suffixName;

            File dir = new File("E:\\idea_项目\\grauation\\student\\word");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(new File(dir, fileName));

            String url = "http://localhost:8080/student/word/" + fileName;
            result.put("url", url);
            studentDao.updateResume(url,id);
            return CommonUtil.successJson(result);
        } catch (Exception e) {
            System.out.println(e);
            return CommonUtil.errorJson(E_400);
        }
    }
}
