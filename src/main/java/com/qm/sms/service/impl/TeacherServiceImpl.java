package com.qm.sms.service.impl;

import com.qm.sms.bean.LoginForm;
import com.qm.sms.bean.Teacher;
import com.qm.sms.dao.AdminMapper;
import com.qm.sms.dao.TeacherMapper;
import com.qm.sms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher login(LoginForm loginForm) {
        return teacherMapper.login(loginForm);
    }

    @Override
    public List<Teacher> selectTeacherList(Teacher teacher) {
        return teacherMapper.selectTeacherList(teacher);
    }

    @Override
    public Teacher findByName(String name) {
        return teacherMapper.findByName(name);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    @Override
    public int delete(Integer[] ids) {
        return teacherMapper.delete(ids);
    }
}
