package edu.just.resource_management_system.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // Redirect from root URL to /home
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";  // Redirect to /home
    }

    // Mapping for the home page
    @GetMapping("/home")
    public String homePage() {
        return "home";  // Your home page view
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index";  //
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }
}
