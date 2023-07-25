package com.qm.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.sms.bean.Grade;
import com.qm.sms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {

    private Map<String,Object> result = new HashMap<>();

    @Autowired
    private GradeService gradeService;
    
    @GetMapping("/goGradeListView")
    public String goGradeListView(){
        return "grade/gradeList";
    }

    @PostMapping("/getGradeList")
    @ResponseBody
    public Map<String,Object> getGradeList(Integer page,Integer rows,String gradename){

        //获取查询的班级
        Grade grade = new Grade();
        grade.setName(gradename);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据模糊查询姓名 查询指定名称 或者班级列表
        List<Grade> gradees = gradeService.selectGradeList(grade);
        //封装查询结果
        PageInfo<Grade> pageInfo = new PageInfo<>(gradees);
        //获取总条数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Grade> list = pageInfo.getList();
        //存储数据
        result.put("total",total);
        result.put("rows",list);
        return result;
    }

    //添加班级信息
    @PostMapping("/addGrade")
    @ResponseBody
    public Map<String,Object> addGrade(Grade grade){
        //判断班级是否存在
        Grade user  =  gradeService.findByName(grade.getName());

        if (user==null ){
            if (gradeService.addGrade(grade)>0){
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

    //修改班级信息
    @PostMapping("/editGrade")
    @ResponseBody
    public Map<String,Object> editGrade(Grade grade){
        //需要排除重名
        Grade user = gradeService.findByName(grade.getName());
        if (user!=null){
            if (!(grade.getId().equals(user.getId()))){
                result.put("success",false);
                result.put("msg","该用户名已存在，请修改重试！");
                return result;
            }

        }

        //修改操作
        if (gradeService.update(grade)>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("msg","修改失败，服务器发生异常！");
        }
        return result;
    }

    //班级删除
    @PostMapping("/deleteGrade")
    @ResponseBody
    public Map<String, Object> deleteGrade(@RequestParam(value = "ids[]") Integer[] ids) {
        if (gradeService.delete(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
