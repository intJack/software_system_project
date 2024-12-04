package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.*;
import edu.just.resource_management_system.service.*;
import edu.just.resource_management_system.util.MD5Util;
import edu.just.resource_management_system.util.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private LanguageService languageService;
    @Autowired
    private TagService tagService;

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
            userService.modifyUserInfo(user.getUserName(), user.getEmail(), user.getPhoneNumber(), user.getId());
            return ResponseEntity.ok("更新成功！");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败，请重试！");
        }
    }
    /**
     * 用户修改密码
     */
    @PostMapping("/ChangePassword")
    public ResponseEntity<Map<String,Object>> ChangePassword(@RequestBody PasswordChangeRequest passwordChangeRequest,
                                                 HttpServletRequest request){
        Long id = (Long) request.getSession().getAttribute("id");
        String userName = (String) request.getSession().getAttribute("userName");
        User user = userService.login(userName,MD5Util.encryptToMD5(passwordChangeRequest.getOldPassword()));
        HashMap<String, Object> response = new HashMap<>();
        if(user!=null){
            userService.modifyUserPassword(MD5Util.encryptToMD5(passwordChangeRequest.getNewPassword()),id);
            response.put("success",true);
            response.put("message","密码修改成功");
            return ResponseEntity.ok(response);
        }else {
            response.put("success", false);
            response.put("message", "原密码输入错误");
            return ResponseEntity.status(401).body(response);
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

    /**
     * 跳转之前存储标签信息和语言信息
     * 跳转到发布资源页面
     * @return 发布资源 页面的视图名称
     */
    @GetMapping("/publish-resource")
    public String publishResourcePage(Model model) {
        List<Tag> allTags = tagService.findAllTags();
        model.addAttribute("allTags",allTags);
        List<Language> allLanguages = languageService.findAllLanguages();
        model.addAttribute("allLanguages",allLanguages);
        return "publish-resource";
    }
    @PostMapping("/submit-resource")
    public ResponseEntity<Map<String,Object>> SubmitResource(@RequestParam("resourceTitle")String resourceTitle,
                                                             @RequestParam("tagName")String tagName,
                                                             @RequestParam("languageName")String languageName,
                                                             @RequestParam("resourceDescription")String resourceDescription,
                                                             HttpServletRequest request){
        Long id = (Long) request.getSession().getAttribute("id");
        HashMap<String, Object> response = new HashMap<>();
        try{
            userService.submitResource(id,resourceTitle,tagName,languageName,resourceDescription);
            response.put("success","添加成功");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "添加失败，请联系系统管理员");
            return ResponseEntity.status(401).body(response);
        }
    }

//    @PostMapping("/submit-resource")
//    public ResponseEntity<Map<String, Object>> submitResource(@RequestParam String resourceTitle,
//                                                              @RequestParam String description) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            // 处理资源发布逻辑
//            // 如果成功
//            response.put("success", true);
//            response.put("message", "资源发布成功！");
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", "资源发布失败：" + e.getMessage());
//        }
//        return ResponseEntity.ok(response);
//    }

//    /**
//     * 提交发布的资源
//     * @param resource 资源对象
//     * @return 提交后的响应
//     */
//    @PostMapping("/submit-resource")
//    public ResponseEntity<String> submitResource(@RequestBody Resource resource) {
//        try {
//            // 假设资源提交后需要保存到数据库
//            resourceService.saveResource(resource);
//            return ResponseEntity.ok("资源发布成功！");
//        } catch (Exception e) {
//            // 处理异常情况，例如保存失败
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("资源发布失败：" + e.getMessage());
//        }
//    }
}
