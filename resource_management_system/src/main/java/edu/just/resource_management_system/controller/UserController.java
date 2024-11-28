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
        return "home";
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @param model 用于传递信息给视图
     * @return 跳转到主页或者显示错误信息
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("userPassword") String password, Model model) {

        // 加密密码
        String encryptedPassword = MD5Util.encryptToMD5(password);

        // 调用服务进行登录验证
        User user = userService.login(userName, encryptedPassword);

        if (user != null) {
            // 登录成功，跳转到主页
            model.addAttribute("user", user);  // 可以把用户信息添加到 Model 里
            return "home";  // 返回主页视图名
        } else {
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "用户名或密码错误");
            return "login";  // 还停留在登录页面
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
