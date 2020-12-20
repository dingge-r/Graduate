package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heeexy.example.dao.RecruitDao;
import com.heeexy.example.dao.StudentDao;
import com.heeexy.example.service.RecruitService;
import com.heeexy.example.service.StudentService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Recruit;
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
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Override
    public Boolean addRecruit(Recruit recruit) {
        int i = recruitDao.insertSelective(recruit);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public JSONObject listRecruit(int page, int size) {
        PageHelper.startPage(page,size);
        List<JSONObject> user=recruitDao.list();
        PageInfo<JSONObject> list = new PageInfo<>(user);
        return  CommonUtil.successPage(list.getPageSize(), list.getList(), list.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRecruit(Recruit recruit) {
        int i = recruitDao.updateByPrimaryKeySelective(recruit);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        int i = recruitDao.deleteByPrimaryKey(id);
        if (i != 1) {
            return false;
        }
        return true;
    }
}
