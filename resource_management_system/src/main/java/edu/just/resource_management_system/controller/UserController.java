package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.LanguageService;
import edu.just.resource_management_system.service.ResourceService;
import edu.just.resource_management_system.service.TagService;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import edu.just.resource_management_system.util.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/SignIn")
    public String SignIn(User user){
        //添加新用户信息
        userService.saveUser(user);
        //返回首页
        return "home";
    }


    @PostMapping("/login1")
    public ResponseEntity<Map<String,Object>> login(@RequestParam("userName") String userName,
                                                    @RequestParam("userPassword") String password,
                                                    HttpServletRequest request) {
        // 加密密码
        String encryptedPassword = MD5Util.encryptToMD5(password);
        // 调用服务进行登录验证
        User user = userService.login(userName, encryptedPassword);

        if (user != null) {
            //往session域存userName 和 id
            request.getSession().setAttribute("userName", user.getUserName());
            request.getSession().setAttribute("id", user.getId());
            String token = TokenUtil.token(user.getUserName(),user.getUserPassword());
            // 设置cookie
            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setHttpOnly(true);
            tokenCookie.setPath("/");
            //加入响应体当中
            HashMap<String, Object> response = new HashMap<>();
            response.put("token",token);
            response.put("Id",user.getId());
            response.put("userName",user.getUserName());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "用户名或密码错误");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    /**
     * @param keyword
     * @param tagName
     * @param languageName
     * @param modelMap
     * @return
     */
    @PostMapping("/search")
    public String searchIdAndKeyword(@RequestParam("keyword")String keyword,
                                     @RequestParam("tagName")String tagName,
                                     @RequestParam("languageName")String languageName,
                                     ModelMap modelMap){
        List<Resource> resources = resourceService.findResourcesByIdsAndKeyword(tagName, languageName, keyword);
        modelMap.addAttribute("resources",resources);
        return "results";
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
    /**
     * 用户跳转到个人信息页面
     */
    @GetMapping("/selfinfo")
    public String SelfInfoPage(HttpServletRequest request,Model model){
        Long id = (Long) request.getSession().getAttribute("id");
        User user = userService.findUserById(id);
        model.addAttribute("user_info",user);
        return "self_info";
    }

    @PostMapping("/updateuser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            System.out.println(user);
            userService.modifyUserInfo(user.getUserName(), user.getEmail(), user.getPhoneNumber(), user.getId());
            return ResponseEntity.ok("更新成功！");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败，请重试！");
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
