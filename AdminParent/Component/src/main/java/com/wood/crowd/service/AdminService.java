package com.wood.crowd.service;

import com.wood.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin login(String loginAcct, String loginPwd);
}
