package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     *审核资源模块实现的流程：
     *  1.展示所有未被审核的资源
     *  2.点击通过/否定处理资源
     *  3.点击否定-- 将status赋值为未通过 #rejectResource
     *  4.通过之后，会将资源调用 #approveResource
     */
    @GetMapping("/resource_check")
    public String resourcecheck(Model model) {
        model.addAttribute("allResources",resourceService.findAllResources());
        return "resource_check";
    }
    @GetMapping("/reject-resource/{id}")
    public String rejectResource(@PathVariable("id") Long id, HttpServletRequest request) {
        Long reviewBy = (Long) request.getSession().getAttribute("id");
        resourceService.rejectResource(id, reviewBy);
        return "redirect:/resource_check"; // 这里是重新加载 resource_check 页面
    }
//    @GetMapping("/reject-resource/{id}")
//    public ResponseEntity<String> rejectResource(@PathVariable("id") Long id, HttpServletRequest request) {
//            Long reviewBy = (Long) request.getSession().getAttribute("id");
//            resourceService.rejectResource(id, reviewBy);
//            return ResponseEntity.ok("资源不通过审核");
////        return ResponseEntity.ok().headers(headers -> headers.setLocation(URI.create("/resource_check"))).body("资源不通过审核");
////
////
//    }


    @GetMapping("/approve-resource/{id}")
    public String approveResource(@PathVariable("id") Long id,
                                  HttpServletRequest request) {
        Long reviewBy = (Long) request.getSession().getAttribute("id");
        Resource resource = resourceService.findTempResourceById(id).get(0);
//        System.out.println(resourceService.findTempResourceById(id));
        resourceService.approveResource(id, reviewBy,resource.getTagName(),
                resource.getLanguageName(),resource.getResourceTitle(),
                resource.getResourceUrl(),resource.getResourceDescription());
        return "redirect:/resource_check"; // 这里是重新加载 resource_check 页面
    }

    // 获取最新的10个资源
    @GetMapping("/forum")
    public String showForumPage(Model model) {
        // 获取最新的 10 个资源
        List<Resource> resources = resourceService.findTop10ByOrderByCreateAtDesc();
        model.addAttribute("resources", resources);  // 将资源传递给前端
        return "forum";  // 返回到 forum 页面
    }

//    @PostMapping
//    public ResponseEntity<Map<String, Object>> submitResourceDetail(@RequestBody Map<String, String> requestData) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            String resourceTitle = requestData.get("resourceTitle");
//            String tagName = requestData.get("tagName");
//            String languageName = requestData.get("languageName");
//            String details = requestData.get("details");
//
//            // 定义文件路径和文件名
//            String filePath = "resources/" + resourceTitle + ".txt";
//            File file = new File(filePath);
//
//            // 确保目录存在
//            file.getParentFile().mkdirs();
//
//            // 写入文件
//            try (FileWriter writer = new FileWriter(file)) {
//                writer.write("资源标题: " + resourceTitle + "\n");
//                writer.write("标签: " + tagName + "\n");
//                writer.write("语言: " + languageName + "\n");
//                writer.write("详情:\n" + details);
//            }
//
//            response.put("success", true);
//            response.put("message", "资源详情已生成并提交！");
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", "发生错误：" + e.getMessage());
//        }
//
//        return ResponseEntity.ok(response);
//    }



}