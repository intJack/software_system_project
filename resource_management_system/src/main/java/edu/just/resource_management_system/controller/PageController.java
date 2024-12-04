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

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

        @GetMapping("/edit-password")
        public String editPassword() {
            return "password";
        }



}
