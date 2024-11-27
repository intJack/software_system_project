package edu.just.resource_management_system.pojo;

import edu.just.resource_management_system.util.MD5Util;
import lombok.*;
import org.springframework.stereotype.Repository;

@Repository
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Long id;
    private String adminName;
    private String adminPassword;

    public Admin(String adminName, String adminPassword) {
        this.adminName = adminName;
        this.adminPassword = MD5Util.encryptToMD5(adminPassword);
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = MD5Util.encryptToMD5(adminPassword);
    }
}
