package com.qm.sms.service.impl;

import com.qm.sms.bean.Admin;
import com.qm.sms.bean.LoginForm;
import com.qm.sms.dao.AdminMapper;
import com.qm.sms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(LoginForm loginForm) {
        return adminMapper.login(loginForm);
    }

    @Override
    public List<Admin> selectAdminList(Admin admin) {
        return adminMapper.selectAdminList(admin);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    @Override
    public Admin findByName(String name) {
            return adminMapper.findByName(name);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public int delete(Integer[] ids) {
        return adminMapper.delete(ids);

    }
}
