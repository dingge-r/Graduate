package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heeexy.example.dao.ArticleDao;
import com.heeexy.example.dao.ArticleMapper;
import com.heeexy.example.service.ArticleService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: hxy
 * @date: 2017/10/24 16:07
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	/**
	 * 新增文章
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean addArticle(Article article) {
		int i = articleMapper.insertSelective(article);
		if (i != 1) {
			return false;
		}
		return true;
	}

	/**
	 * 文章列表
	 */
	@Override
	public JSONObject listArticle(int page, int size) {
		PageHelper.startPage(page,size);
		List<JSONObject> user=articleMapper.list();
		PageInfo<JSONObject> list = new PageInfo<>(user);
		return  CommonUtil.successPage(list.getPageSize(), list.getList(), list.getTotal());
	}

	/**
	 * 更新文章
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateArticle(Article article) {
		int i = articleMapper.updateByPrimaryKeySelective(article);
		if (i != 1) {
			return false;
		}
		return true;
	}

    @Override
    public boolean delete(Integer id) {
        int i = articleMapper.deleteByPrimaryKey(id);
        if (i != 1) {
            return false;
        }
        return true;
    }
}
