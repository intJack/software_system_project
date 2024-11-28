package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import edu.just.resource_management_system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/SignIn")
    public String SignIn(User user){
        //已经在setUserPassword方法对密码进行了加密
//        user.setUserPassword(MD5Util.encryptToMD5(user.getUserPassword()));
        //存入数据库
        userService.saveUser(user);
        //返回首页
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<HttpRequest> Login(@RequestParam("userName")String userName,
                                             @RequestParam("userPassword")String password){
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

    /**
     * 用户实现对语言的查询 点击语言相关的资源 然后跳转到查询结果页
     * @return
     */
    @RequestMapping("/search/{language}")
    public String searchLanguage(Model model){

        //跳转到查询结果页
        return "";
    }
}
