package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     *审核资源模块实现的流程：
     *  1.展示所有未被审核的资源
     *  2.点击通过/否定处理资源
     *  3.点击否定--直接删除 将status赋值为未通过 点击通过 将status赋值为通过
     *  4.通过之后，会将资源调用 void approveResource(Long id);即可（里面封装处理通过资源的业务逻辑）
     */
    @GetMapping("/resource_check")
    public String resourcecheck(Model model) {
        model.addAttribute("allResources",resourceService.findAllResources());
        return "resource_check";
    }

//    @PostMapping("/save-temp-resource")
//    public ResponseEntity<String> saveTempResource(@RequestBody Resource resource) {
//        resourceService.saveTempResource(resource);
//        return ResponseEntity.ok("Resource saved for review");
//    }
//
//    @PostMapping("/approve-resource/{id}")
//    public ResponseEntity<String> approveResource(@PathVariable Long id, @RequestBody Long reviewBy) {
//        resourceService.approveResource(id, reviewBy);
//        return ResponseEntity.ok("Resource approved successfully");
//    }
//
    @GetMapping("/reject-resource/{id}")
    public ResponseEntity<String> rejectResource(@PathVariable("id") Long id) {
        System.out.println(id);
//        System.out.println(reviewBy);
//        resourceService.rejectResource(id, reviewBy);
        return ResponseEntity.ok("Resource rejected successfully");
    }
}