package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heeexy.example.dao.NoticeDao;
import com.heeexy.example.service.NoticeService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Boolean addNotice(Notice notice) {
        int i = noticeDao.insertSelective(notice);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public JSONObject listNotice(int page, int size) {
        PageHelper.startPage(page,size);
        List<JSONObject> user=noticeDao.list();
        PageInfo<JSONObject> list = new PageInfo<>(user);
        return  CommonUtil.successPage(list.getPageSize(), list.getList(), list.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateNotice(Notice notice) {
        int i = noticeDao.updateByPrimaryKeySelective(notice);
        if (i != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        int i = noticeDao.deleteByPrimaryKey(id);
        if (i != 1) {
            return false;
        }
        return true;
    }
}
