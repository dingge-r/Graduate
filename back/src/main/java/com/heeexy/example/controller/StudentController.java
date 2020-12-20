package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.controller.common.BaseController;
import com.heeexy.example.service.StudentService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.heeexy.example.util.constants.ErrorEnum.*;

@Api(tags = "学生信息管理")
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private StudentService studentService;

    /**
     * 查询学生信息列表
     */
    @ApiOperation("查看学生信息列表")
    @RequiresPermissions("article:list")
    @GetMapping("/listStudent")
    public JSONObject listStudent(@RequestParam(value = "pageNum",defaultValue ="1") int page,
                                  @RequestParam(value = "pageRow",defaultValue = "10") int size) {
        return studentService.listStudent(page,size);
    }

    /**
     * 新增学生
     */
    @ApiOperation("添加学生")
    @RequiresPermissions("article:add")
    @PostMapping("/addStudent")
    public JSONObject addStudent(@RequestBody Student student) {
        try {
            if (studentService.addStudent(student)) {
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
     * 修改学生信息
     */
    @ApiOperation("更新学生信息")
    @RequiresPermissions("article:update")
    @PostMapping("/updateStudent")
    public JSONObject updateStudent(@RequestBody Student student) {
        try {
            if (student.getId() == null) {
                return CommonUtil.fail("缺少必要参数 ：id");
            } else if (studentService.updateStudent(student)) {
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
     * 删除学生信息
     */
    @ApiOperation("删除学生信息")
    @RequiresPermissions("article:delete")
    @DeleteMapping("/deleteStudent")
    public JSONObject deleteStudent(@RequestParam(name = "id") Integer id) {
        try {
            if (studentService.delete(id)) {
                return CommonUtil.successJson();
            } else {
                return CommonUtil.errorJson(E_404);
            }
        } catch (Exception e) {
            LOG.error("Delete Student info error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }

    /**
     * 上传简历
     * @param file
     * @return
     */
    @RequiresPermissions("article:delete")
    @PostMapping("uploadWord")
    public JSONObject uploadWord(@RequestParam(name = "file") MultipartFile file,
                                 @RequestParam(name = "sid") Integer sid){
        try {
            if (file == null) {
                return CommonUtil.fail("文件不为空");
            }else {
                return studentService.uploadWord(file,sid);
            }
        }catch (Exception e){
            LOG.error("Delete Student info error.", e);
            return CommonUtil.errorJson(E_500);
        }
    }
}
