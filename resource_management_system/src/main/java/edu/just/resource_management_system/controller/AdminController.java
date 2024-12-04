package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.AdminService;
import edu.just.resource_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;


    /**
     * 管理员进入管理员后台管理界面
     * @return
     */
    @GetMapping("/admin")
    public String AdminLoginPage(Model model){
        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers",allUsers);
        return "admin_management";
    }

    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        adminService.deleteUserById(id);
        return "redirect:/admin";
    }

}
