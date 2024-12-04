package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    /**
     * 管理员登录功能
     * @param userName 用户名
     * @param Password 密码
     * @return null or not null
     */
    Admin login(@Param("adminName")String userName,@Param("adminPassword")String Password);

    /**
     * 查找 某位后台 管理员信息
     * @param id
     * @return
     */
    Admin selectAdminById(Long id);

    /**
     * 根据用户id 管理员
     * @param admin 修改过的管理员
     */
    void ModifyAdminInfo(@Param("admin") Admin admin);

    /**
     * 添加新的后台管理员
     * @param admin
     */
    void attachAdmin(Admin admin);

    /**
     * 删除指定用户
     * @param id 用户id
     */
    void deleteUserById(Long id);


}
