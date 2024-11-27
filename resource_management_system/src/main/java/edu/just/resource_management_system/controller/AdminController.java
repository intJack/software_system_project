package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.service.AdminService;
import edu.just.resource_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;




}
