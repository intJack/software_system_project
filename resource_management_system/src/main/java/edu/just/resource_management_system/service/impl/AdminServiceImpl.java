package edu.just.resource_management_system.service.impl;

import edu.just.resource_management_system.mapper.AdminMapper;
import edu.just.resource_management_system.mapper.UserMapper;
import edu.just.resource_management_system.pojo.Admin;
import edu.just.resource_management_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Admin login(String userName, String password) {
        return adminMapper.login(userName,password);
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.attachAdmin(admin);
    }

    @Override
    public void modifyAdminInfo(Admin admin) {
        adminMapper.ModifyAdminInfo(admin);
    }

    @Override
    public Admin selectAdminById(Long id) {
        return adminMapper.selectAdminById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        adminMapper.deleteUserById(id);
    }

    @Override
    public void approveResource(Long id) {
    }
}
