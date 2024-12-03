package edu.just.resource_management_system.controller;



import edu.just.resource_management_system.service.LanguageService;
import edu.just.resource_management_system.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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

    /**
     * 跳转到search界面 往modelmap加入所有标签和语言
     * @return search.html
     */
    @Autowired
    TagService tagService;
    @Autowired
    LanguageService languageService;
    @GetMapping("/search")
    public String searchPage(ModelMap modelMap) {
        modelMap.addAttribute("allTags",tagService.findAllTags());
        modelMap.addAttribute("allLanguages",languageService.findAllLanguages());
        return "search";
    }
}
