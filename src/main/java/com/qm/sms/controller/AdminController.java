package com.qm.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.sms.bean.Admin;
import com.qm.sms.service.AdminService;
import com.qm.sms.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController{

    private Map<String,Object> result = new HashMap<>();

    @Autowired
    private AdminService adminService;

    @GetMapping("/goAdminListView")
    public String goAdminListView(){
        return "admin/adminList";
    }

    @PostMapping("/getAdminList")
    @ResponseBody
    public Map<String,Object> getAdminList(Integer page,Integer rows,String username){

        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据模糊查询姓名 查询指定名称 或者用户列表
        List<Admin> adminList = adminService.selectAdminList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        //获取总条数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Admin> list = pageInfo.getList();
        //存储数据
        result.put("total",total);
        result.put("rows",list);
        return result;
    }

    //添加管理员信息
    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String,Object> addAdmin(Admin admin){
        //判断用户名是否存在
        Admin user  =  adminService.findByName(admin.getName());
        if (user==null ){
            if (adminService.addAdmin(admin)>0){
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

    //修改管理员信息
    @PostMapping("/editAdmin")
    @ResponseBody
    public Map<String,Object> editAdmin(Admin admin){
        //需要排除重名
        Admin user = adminService.findByName(admin.getName());
        if (user!=null){
            if (!(admin.getId().equals(user.getId()))){
                result.put("success",false);
                result.put("msg","该用户名已存在，请修改重试！");
                return result;
            }

        }

        //修改操作
        if (adminService.update(admin)>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("msg","修改失败，服务器发生异常！");
        }
        return result;
    }

    //管理员删除
    @PostMapping("/deleteAdmin")
    @ResponseBody
    public Map<String, Object> deleteAdmin(@RequestParam(value = "ids[]") Integer[] ids) {
        if (adminService.delete(ids) > 0) {
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
