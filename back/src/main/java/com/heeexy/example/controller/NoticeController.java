package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.controller.common.BaseController;
import com.heeexy.example.service.NoticeService;
import com.heeexy.example.service.StudentService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Notice;
import com.heeexy.example.util.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

import java.sql.Date;

import static com.heeexy.example.util.constants.ErrorEnum.*;

@Api(tags ="公告管理")
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private NoticeService noticeService;

    /**
     * 公告列表
     */
    @ApiOperation("公告列表")
    @RequiresPermissions("article:list")
    @GetMapping("/listNotice")
    public JSONObject listNotice(@RequestParam(value = "pageNum",defaultValue ="1") int page,
                                  @RequestParam(value = "pageRow",defaultValue = "10") int size) {
        return noticeService.listNotice(page,size);
    }

    /**
     * 新增公告
     */
    @ApiOperation("添加公告")
    @RequiresPermissions("article:add")
    @PostMapping("/addNotice")
    public JSONObject addNotice(@RequestBody Notice notice) {
        try {
            if (noticeService.addNotice(notice)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_400);
            }
        } catch (Exception e) {
            LOG.error("Save Notice error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }

    /**
     * 修改公告信息
     */
    @ApiOperation("更新公告信息")
    @RequiresPermissions("article:update")
    @PostMapping("/updateNotice")
    public JSONObject updateNotice(@RequestBody Notice notice) {
        try {
            if (notice.getId() == null) {
                return CommonUtil.fail("缺少必要参数 ：id");
            } else if (noticeService.updateNotice(notice)) {
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
     * 删除公告信息
     */
    @ApiOperation("删除公告信息")
    @RequiresPermissions("article:delete")
    @DeleteMapping("/deleteNotice")
    public JSONObject deleteNotice(@RequestParam(name = "id") Integer id) {
        try {
            if (noticeService.delete(id)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_404);
            }
        } catch (Exception e) {
            LOG.error("Delete Notice info error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }
}
