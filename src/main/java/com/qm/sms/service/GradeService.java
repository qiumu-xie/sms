package com.qm.sms.service;

import com.qm.sms.bean.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> selectAll();

    List<Grade> selectGradeList(Grade grade);

    int addGrade(Grade grade);

    Grade findByName(String name);

    int update(Grade grade);

    int delete(Integer[] ids);

}
