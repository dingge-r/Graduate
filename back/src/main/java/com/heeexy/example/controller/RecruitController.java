package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.controller.common.BaseController;
import com.heeexy.example.service.NoticeService;
import com.heeexy.example.service.RecruitService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Notice;
import com.heeexy.example.util.model.Recruit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.heeexy.example.util.constants.ErrorEnum.*;

@Api(tags ="招聘信息管理")
@RestController
@RequestMapping("/recruit")
public class RecruitController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RecruitService recruitService;

    /**
     * 招聘信息列表
     */
    @ApiOperation("招聘信息列表")
    @RequiresPermissions("article:list")
    @GetMapping("/listRecruit")
    public JSONObject listRecruit(@RequestParam(value = "pageNum",defaultValue ="1") int page,
                                  @RequestParam(value = "pageRow",defaultValue = "10") int size) {
        return recruitService.listRecruit(page,size);
    }

    /**
     * 新增招聘信息
     */
    @ApiOperation("添加招聘信息")
    @RequiresPermissions("article:add")
    @PostMapping("/addRecruit")
    public JSONObject addRecruit(@RequestBody Recruit recruit) {
        try {
            if (recruitService.addRecruit(recruit)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_400);
            }
        } catch (Exception e) {
            LOG.error("Save Recruit error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }

    /**
     * 修改招聘信息
     */
    @ApiOperation("更新招聘信息")
    @RequiresPermissions("article:update")
    @PostMapping("/updateRecruit")
    public JSONObject updateNotice(@RequestBody Recruit recruit) {
        try {
            if (recruit.getId() == null) {
                return CommonUtil.fail("缺少必要参数 ：id");
            } else if (recruitService.updateRecruit(recruit)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_404);
            }
        } catch (Exception e) {
            LOG.error("Update Notice error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }

    /**
     * 删除招聘信息
     */
    @ApiOperation("删除招聘信息")
    @RequiresPermissions("article:delete")
    @DeleteMapping("/deleteRecruit")
    public JSONObject deleteNotice(@RequestParam(name = "id") Integer id) {
        try {
            if (recruitService.delete(id)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_404);
            }
        } catch (Exception e) {
            LOG.error("Delete Recruit info error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }
}
