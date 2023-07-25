package com.qm.sms.service.impl;

import com.qm.sms.bean.Grade;
import com.qm.sms.dao.GradeMapper;
import com.qm.sms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeServiceIImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public List<Grade> selectGradeList(Grade grade) {
        return gradeMapper.selectGradeList(grade);
    }

    @Override
    public int addGrade(Grade grade) {
        return gradeMapper.addGrade(grade);
    }

    @Override
    public Grade findByName(String name) {
        return gradeMapper.findByName(name);
    }

    @Override
    public int update(Grade grade) {
        return gradeMapper.update(grade);
    }

    @Override
    public int delete(Integer[] ids) {
        return gradeMapper.delete(ids);
    }
}
