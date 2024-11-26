package edu.just.resource_management_system.service;

import edu.just.resource_management_system.pojo.Admin;

public interface AdminService {
    Admin login(String userName,String password);
    void saveAdmin(Admin admin);
    void modifyAdminInfo(Admin admin);
    Admin selectAdminById(Long id);
}
