package com.wood.crowd.service.impl;

import com.wood.crowd.constant.Constants;
import com.wood.crowd.entity.Admin;
import com.wood.crowd.entity.AdminExample;
import com.wood.crowd.exception.LoginFailedException;
import com.wood.crowd.mapper.AdminMapper;
import com.wood.crowd.service.AdminService;
import com.wood.crowd.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin login(String loginAcct, String loginPwd) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (CollectionUtils.isEmpty(admins)) {
            throw new LoginFailedException(Constants.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1) {
            throw new LoginFailedException(Constants.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new LoginFailedException(Constants.MESSAGE_LOGIN_FAILED);
        }
        String password = admin.getUserPwd();
        if (!Objects.equals(CommonUtils.md5(loginPwd), password)) {
            throw new LoginFailedException(Constants.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }
}
