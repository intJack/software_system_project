package edu.just.resource_management_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    //password是用户的 加密密文
    private String userPassword;
    private String email;
    private String phoneNumber;

    public User(String userName, String userPassword, String email, String phoneNumber) {
        this.userName = userName;
        //需要进行加密
        this.userPassword = userPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
