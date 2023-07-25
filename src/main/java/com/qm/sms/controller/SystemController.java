package com.qm.sms.controller;

import com.qm.sms.bean.Admin;
import com.qm.sms.bean.LoginForm;
import com.qm.sms.bean.Student;
import com.qm.sms.bean.Teacher;
import com.qm.sms.service.AdminService;
import com.qm.sms.service.StudentService;
import com.qm.sms.service.TeacherService;
import com.qm.sms.util.CreateVerifiCodeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {

    private Map<String,Object> result = new HashMap<>();

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    //生成验证码
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){
        //生成验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //生成随机字符
        String verifiCode = String.valueOf(CreateVerifiCodeImage.getVerifiCode());
        //获取session,转存随机字符
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        //将验证码输出到登录页面
        try {
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(LoginForm loginForm,HttpServletRequest request){
        HttpSession session = request.getSession();
        String verifiCode = (String) session.getAttribute("verifiCode");

        if ("".equals(verifiCode)) {
            result.put("success",false);
            result.put("msg","已超时");
            return result;
        } else if (!(loginForm.getVerifiCode().equalsIgnoreCase(verifiCode))) {
            result.put("success",false);
            result.put("msg","验证码输入错误");
            return result;
        }
        session.removeAttribute("verifiCode");

        switch (loginForm.getUserType()){
            //管理员登录
            case "1":
                try {
                    Admin admin = adminService.login(loginForm);
                    if (admin != null) {
                        session.setAttribute("userInfo",admin);
                        session.setAttribute("userType",loginForm.getUserType());
                        result.put("success",true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success",false);
                    result.put("msg","登录失败，服务器异常！");
                    return result;
                }
                break;
            case "2":
                try {
                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        session.setAttribute("userInfo",student);
                        session.setAttribute("userType",loginForm.getUserType());
                        result.put("success",true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success",false);
                    result.put("msg","登录失败，服务器异常！");
                    return result;
                }
                break;
            case "3":
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        session.setAttribute("userInfo",teacher);
                        session.setAttribute("userType",loginForm.getUserType());
                        result.put("success",true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success",false);
                    result.put("msg","登录失败，服务器异常！");
                    return result;
                }
                break;
        }
        //登录失败
        result.put("success",false);
        result.put("msg","登录失败，输入用户名或者密码错误！");
        return result;
    }

    //登录成功跳转到主页面
    @GetMapping("/goSystemMainView")
    public String goSystemMainView(){
        return "system/main";  //   /system/main.jsp
    }

    //退出登录
    @GetMapping("/loginOut")
    public void loginOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
        session.removeAttribute("userType");
        try {
            response.sendRedirect("../index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
