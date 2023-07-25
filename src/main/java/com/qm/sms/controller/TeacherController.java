package com.qm.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.sms.bean.Teacher;
import com.qm.sms.service.ClazzService;
import com.qm.sms.service.TeacherService;
import com.qm.sms.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private Map<String,Object> result = new HashMap<>();

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/goTeacherListView")
    public ModelAndView goAdminListView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clazzList",clazzService.selectAll());
        modelAndView.setViewName("teacher/teacherList");
        return modelAndView;
    }

    @PostMapping("/getTeacherList")
    @ResponseBody
    public Map<String,Object> getAdminList(Integer page,Integer rows,String teachername,String clazzname){

        //获取查询的用户名
        Teacher teacher = new Teacher();
        teacher.setName(teachername);
        teacher.setClazz_name(clazzname);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据模糊查询姓名 查询指定名称 或者用户列表
        List<Teacher> teachers = teacherService.selectTeacherList(teacher);
        //封装查询结果
        PageInfo<Teacher> pageInfo = new PageInfo<>(teachers);
        //获取总条数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Teacher> list = pageInfo.getList();
        //存储数据
        result.put("total",total);
        result.put("rows",list);
        return result;
    }

    //添加学生信息
    @PostMapping("/addTeacher")
    @ResponseBody
    public Map<String,Object> addTeacher(Teacher teacher){
        //判断用户名是否存在
        Teacher user  =  teacherService.findByName(teacher.getName());

        if (user==null ){
            if (teacherService.addTeacher(teacher)>0){
                result.put("success",true);
            }else {
                result.put("success",false);
                result.put("msg","服务器发生异常！");
            }
        }else {
            result.put("success",false);
            result.put("msg","该用户名已存在，请修改重试！");
        }
        return result;
    }

    //修改学生信息
    @PostMapping("/editTeacher")
    @ResponseBody
    public Map<String,Object> editTeacher(Teacher teacher){
        //需要排除重名
        Teacher user = teacherService.findByName(teacher.getName());
        if (user!=null){
            if (!(teacher.getId().equals(user.getId()))){
                result.put("success",false);
                result.put("msg","该用户名已存在，请修改重试！");
                return result;
            }

        }

        //修改操作
        if (teacherService.update(teacher)>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("msg","修改失败，服务器发生异常！");
        }
        return result;
    }

    //学生删除
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public Map<String, Object> deleteTeacher(@RequestParam(value = "ids[]") Integer[] ids) {
        if (teacherService.delete(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
    @PostMapping("/uploadPhoto")
    @ResponseBody
    public Map<String, Object>uploadPhoto(MultipartFile photo, HttpServletRequest request) {
        //存储头像到本地目录
        final String dirpath = request.getServletContext().getRealPath("/upload/admin_portrait/");
        //存储头像到项目发布目录
        final String portraitPath = request.getServletContext().getContextPath() + "/upload/admin_portrait/";
        //返回头像上传结果
        return UploadFile.getUploadResult(photo, dirpath, portraitPath);
    }

}
