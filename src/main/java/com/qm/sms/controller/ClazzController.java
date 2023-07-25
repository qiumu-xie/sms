package com.qm.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.sms.bean.Clazz;
import com.qm.sms.service.ClazzService;
import com.qm.sms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(("/clazz"))
public class ClazzController {
    private Map<String,Object> result = new HashMap<>();

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private GradeService gradeService;

    @GetMapping("/goClazzListView")
    public ModelAndView goClazzListView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("gradeList",gradeService.selectAll());
        modelAndView.setViewName("clazz/clazzList");
        return modelAndView;
    }

    @PostMapping("/getClazzList")
    @ResponseBody
    public Map<String,Object> getClazzList(Integer page,Integer rows,String clazzname,String gradename){

        //获取查询的班级
        Clazz clazz = new Clazz(clazzname,gradename);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据模糊查询姓名 查询指定名称 或者班级列表
        List<Clazz> clazzes = clazzService.selectClazzList(clazz);
        //封装查询结果
        PageInfo<Clazz> pageInfo = new PageInfo<>(clazzes);
        //获取总条数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Clazz> list = pageInfo.getList();
        //存储数据
        result.put("total",total);
        result.put("rows",list);
        return result;
    }

    //添加班级信息
    @PostMapping("/addClazz")
    @ResponseBody
    public Map<String,Object> addClazz(Clazz clazz){
        //判断班级是否存在
        Clazz user  =  clazzService.findByName(clazz.getName());

        if (user==null ){
            if (clazzService.addClazz(clazz)>0){
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
    @PostMapping("/editClazz")
    @ResponseBody
    public Map<String,Object> editClazz(Clazz clazz){
        //需要排除重名
        Clazz user = clazzService.findByName(clazz.getName());
        if (user!=null){
            if (!(clazz.getId().equals(user.getId()))){
                result.put("success",false);
                result.put("msg","该用户名已存在，请修改重试！");
                return result;
            }

        }

        //修改操作
        if (clazzService.update(clazz)>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("msg","修改失败，服务器发生异常！");
        }
        return result;
    }

    //班级删除
    @PostMapping("/deleteClazz")
    @ResponseBody
    public Map<String, Object> deleteClazz(@RequestParam(value = "ids[]") Integer[] ids) {
        if (clazzService.delete(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
