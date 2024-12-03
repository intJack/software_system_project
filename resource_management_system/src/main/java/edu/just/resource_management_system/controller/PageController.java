package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.service.LanguageService;
import edu.just.resource_management_system.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PageController的类不会做复杂业务逻辑处理，作为页面与页面之间的衔接
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

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @GetMapping("/adminlogin")
    public String AdminLoginPage(){
        return "admin_login";
    }

}
