package com.qm.sms.service;

import com.qm.sms.bean.Clazz;

import java.util.List;

public interface ClazzService {
    List<Clazz> selectClazzList(Clazz clazz);

    int addClazz(Clazz clazz);

    Clazz findByName(String name);

    int update(Clazz clazz);

    int delete(Integer[] ids);

    List<Clazz> selectAll();

}
