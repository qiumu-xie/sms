package com.qm.sms.dao;

import com.qm.sms.bean.LoginForm;
import com.qm.sms.bean.Teacher;

import java.util.List;

public interface TeacherMapper {
    Teacher login (LoginForm loginForm);
    List<Teacher> selectTeacherList(Teacher teacher);

    Teacher findByName(String name);

    int addTeacher(Teacher teacher);

    int update(Teacher teacher);

    int delete(Integer[] ids);
}
