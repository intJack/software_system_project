package edu.just.resource_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PageController的类不会做复杂业务逻辑处理，仅仅作为页面与页面之间的衔接
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    // Mapping for the home page
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
    //用户登录
    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }
    // 跳转到管理员登录页面
    @GetMapping("/managerlogin")
    public String showManagerLoginPage() {return "manager_login";}

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/edit-password")
    public String editPassword() {
            return "password";
        }

    @GetMapping("/resource_check")
    public String resourcecheck() {
        return "resource_check";
    }
    @GetMapping("/manager_selfinfo")
    public String managerselfinfo() {
        return "manager_selfinfo";
    }
}
