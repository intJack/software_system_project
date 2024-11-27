package edu.just.resource_management_system.pojo;

import edu.just.resource_management_system.util.MD5Util;
import lombok.*;
import org.springframework.stereotype.Repository;

@Repository
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    //password是用户的 加密密文
    private String userPassword;
    @Setter
    private String email;
    @Setter
    private String phoneNumber;

    public User(String userName, String userPassword, String email, String phoneNumber) {
        this.userName = userName;
        //需要进行加密
        this.userPassword = MD5Util.encryptToMD5(userPassword);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = MD5Util.encryptToMD5(userPassword);
    }

}
