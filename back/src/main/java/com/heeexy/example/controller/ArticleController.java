package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.controller.common.BaseController;
import com.heeexy.example.service.ArticleService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.heeexy.example.util.constants.ErrorEnum.E_400;
import static com.heeexy.example.util.constants.ErrorEnum.E_404;
import static com.heeexy.example.util.constants.ErrorEnum.E_500;

/**
 * @author: hxy
 * @description: 文章相关Controller
 * @date: 2017/10/24 16:04
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
	private ArticleService articleService;

	/**
	 * 查询文章列表
	 */
	@ApiOperation("查看文章列表")
	@RequiresPermissions("article:list")
	@GetMapping("/listArticle")
	public JSONObject listArticle(@RequestParam(value = "pageNum",defaultValue ="1") int page,
								  @RequestParam(value = "pageRow",defaultValue = "10") int size) {
		return articleService.listArticle(page,size);
	}

	/**
	 * 新增文章
	 */
	@ApiOperation("添加文章")
	@RequiresPermissions("article:add")
	@PostMapping("/addArticle")
	public JSONObject addArticle(@RequestBody Article article) {
        try {
            if (articleService.addArticle(article)) {
                return CommonUtil.successJson();
            } else {
				return CommonUtil.errorJson(E_400);
            }
        } catch (Exception e) {
            LOG.error("Save User error.", e);
			return CommonUtil.errorJson(E_500);
        }
	}

	/**
	 * 修改文章
	 */
	@ApiOperation("更新文章")
	@RequiresPermissions("article:update")
	@PostMapping("/updateArticle")
	public JSONObject updateArticle(@RequestBody Article article) {
		try {
			if (article.getId() == null) {
				return CommonUtil.fail("缺少必要参数 ：id");
			} else if (articleService.updateArticle(article)) {
				return CommonUtil.successJson();
			} else {
				return CommonUtil.errorJson(E_404);
			}
		} catch (Exception e) {
			LOG.error("Update User error.", e);
			return CommonUtil.errorJson(E_500);
		}
	}

    /**
     * 删除文章
     */
    @ApiOperation("删除文章")
    @RequiresPermissions("article:delete")
    @DeleteMapping("/deleteArticle")
    public JSONObject deleteArticle(@RequestParam(name = "id") Integer id) {
        try {
            if (articleService.delete(id)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_404);
            }
        } catch (Exception e) {
            LOG.error("Delete User error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }

}
