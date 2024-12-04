package edu.just.resource_management_system.service;

import edu.just.resource_management_system.pojo.Admin;

public interface AdminService {
    Admin login(String userName,String password);
    void saveAdmin(Admin admin);
    void modifyAdminInfo(Admin admin);
    Admin selectAdminById(Long id);
    void deleteUserById(Long id);
    /**
     * 管理员审核用户上传资源
     * 1.通过前端 传来的资源id 来进行添加操作
     * 2.首先在tag_language表 查询有没有(tagName,languageName)这个组合的id
     *  1.有则获得该id 在添加到resource表后() 把tag_language_id设置该id
     *  2.没有则 新增id 在添加到resource表后 把tag_language_id设置该id
     *   然后去查找这个tagName是否在tag表从未出现过，是就添加，否就什么也不做
     *  同理对于language表也是同样的操作
     *
     */
    void approveResource(Long id);
}
