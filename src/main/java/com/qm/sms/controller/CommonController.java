package com.qm.sms.controller;

import com.qm.sms.bean.Admin;
import com.qm.sms.bean.Student;
import com.qm.sms.bean.Teacher;
import com.qm.sms.service.AdminService;
import com.qm.sms.service.StudentService;
import com.qm.sms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController {
    private Map<String,Object> result = new HashMap<>();

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/goSettingView")
    public String goSettingView(){
        return "common/settings";
    }

    @PostMapping("/editPassword")
    @ResponseBody
    public Map<String,Object> editPassword(HttpServletRequest request,String oldPassword, String newPassword) {
        HttpSession session = request.getSession();

        switch ((String) session.getAttribute("userType")){
            case "1":
                Admin admin = (Admin) session.getAttribute("userInfo");
                if (!admin.getPassword().equals(oldPassword)){
                    result.put("success", false);
                    result.put("msg", "输入密码错误，请修改重试！");
                    return result;
                }
                admin.setPassword(newPassword);
                //修改操作
                if (adminService.update(admin)>0){
                    result.put("success",true);
                }else {
                    result.put("success",false);
                    result.put("msg","修改失败，服务器发生异常！");
                }
                break;
            case "2":
                Student student = (Student) session.getAttribute("userInfo");
                if (!student.getPassword().equals(oldPassword)){
                    result.put("success", false);
                    result.put("msg", "输入密码错误，请修改重试！");
                    return result;
                }
                student.setPassword(newPassword);
                //修改操作
                if (studentService.update(student)>0){
                    result.put("success",true);
                }else {
                    result.put("success",false);
                    result.put("msg","修改失败，服务器发生异常！");
                }
                break;
            case "3":
                Teacher teacher = (Teacher) session.getAttribute("userInfo");
                if (!teacher.getPassword().equals(oldPassword)){
                    result.put("success", false);
                    result.put("msg", "输入密码错误，请修改重试！");
                    return result;
                }
                teacher.setPassword(newPassword);
                //修改操作
                if (teacherService.update(teacher)>0){
                    result.put("success",true);
                }else {
                    result.put("success",false);
                    result.put("msg","修改失败，服务器发生异常！");
                }
                break;
        }
        return result;
    }
}
