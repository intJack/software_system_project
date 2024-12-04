package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Admin;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.AdminService;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import edu.just.resource_management_system.util.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private Admin admin;

    @PostMapping("/login2")
    public ResponseEntity<Map<String,Object>> login(@RequestParam("adminName") String adminName,
                                                    @RequestParam("adminPassword") String adminPassword,
                                                    HttpServletRequest request) {
        // 加密密码
        String encryptedPassword = MD5Util.encryptToMD5(adminPassword);
        // 调用服务进行登录验证
        Admin admin = adminService.login(adminName, encryptedPassword);

        if (admin != null) {
            //往session域存userName 和 id
            request.getSession().setAttribute("adminName", admin.getAdminName());
            request.getSession().setAttribute("id", admin.getId());
            String token = TokenUtil.token(admin.getAdminName(),admin.getAdminPassword());
            // 设置cookie
            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setHttpOnly(true);
            tokenCookie.setPath("/");
            //加入响应体当中
            HashMap<String, Object> response = new HashMap<>();
            response.put("token",token);
            response.put("Id",admin.getId());
            response.put("userName",admin.getAdminName());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "用户名或密码错误");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
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

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        adminService.deleteUserById(id);
        return "redirect:/admin";
    }



}
