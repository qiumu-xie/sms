package com.qm.sms.dao;

import com.qm.sms.bean.Admin;
import com.qm.sms.bean.LoginForm;
import com.qm.sms.bean.Student;

import java.util.List;

public interface StudentMapper {
    Student login(LoginForm loginForm);

    List<Student> selectStudentList(Student student);

    Student findByName(String name);

    int addStudent(Student student);

    int update(Student student);

    int delete(Integer[] ids);
}
