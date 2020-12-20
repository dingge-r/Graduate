package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Notice;

public interface NoticeService {
    /**
     * 新增公告
     */
    Boolean addNotice(Notice notice);

    /**
     * 公告列表
     */
    JSONObject listNotice(int page, int size);

    /**
     * 更新公告
     */
    Boolean updateNotice(Notice notice);

    /**
     * 删除公告
     * @param id
     * @return
     */
    boolean delete(Integer id);
}
