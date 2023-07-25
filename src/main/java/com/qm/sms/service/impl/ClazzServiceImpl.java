package com.qm.sms.service.impl;

import com.qm.sms.bean.Clazz;
import com.qm.sms.dao.ClazzMapper;
import com.qm.sms.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> selectClazzList(Clazz clazz) {
        return clazzMapper.selectClazzList(clazz);
    }

    @Override
    public int addClazz(Clazz clazz) {
        return clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz findByName(String name) {
        return clazzMapper.findByName(name);
    }

    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    @Override
    public int delete(Integer[] ids) {
        return clazzMapper.delete(ids);
    }

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }


}
