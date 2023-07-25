package com.qm.sms.dao;

import com.qm.sms.bean.Grade;

import java.util.List;

public interface GradeMapper {

    List<Grade> selectAll();

    List<Grade> selectGradeList(Grade grade);

    int addGrade(Grade grade);

    Grade findByName(String name);

    int update(Grade grade);

    int delete(Integer[] ids);
}
