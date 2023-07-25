package com.qm.sms.dao;

import com.qm.sms.bean.Admin;
import com.qm.sms.bean.LoginForm;

import java.util.List;

public interface AdminMapper {
    Admin login(LoginForm loginForm);

    List<Admin> selectAdminList(Admin admin);

    int addAdmin(Admin admin);

    Admin findByName(String name);

    int update(Admin admin);

    int delete(Integer[] ids);
}
