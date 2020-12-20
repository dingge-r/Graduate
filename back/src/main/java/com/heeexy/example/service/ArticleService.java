package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Article;

/**
 * @author: hxy
 * @date: 2017/10/24 16:06
 */
public interface ArticleService {
	/**
	 * 新增文章
	 */
	Boolean addArticle(Article article);

	/**
	 * 文章列表
	 */
	JSONObject listArticle(int page, int size);

	/**
	 * 更新文章
	 */
	Boolean updateArticle(Article article);

    boolean delete(Integer id);
}
