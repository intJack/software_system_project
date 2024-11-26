package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import edu.just.resource_management_system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/SignIn")
    public String SignIn(User user){
        //对密码进行MD5算法加密
        user.setUserPassword(MD5Util.encryptToMD5(user.getUserPassword()));
        //存入数据库
        userService.saveUser(user);
        //返回首页
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<HttpRequest> Login(@RequestParam("userName")String userName,
                                             @RequestParam("password")String password){
        User user = userService.login(userName, MD5Util.encryptToMD5(password));
        if (user != null) {
            String token = TokenUtil.token(userName, password);
            // 在请求头添加token
            return ResponseEntity.ok().header("Authorization", token).build();
        } else {
            // 401 代表 授权失败
            return ResponseEntity.status(401).build();
        }
    }
}
