package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
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
     *  3.点击否定-- 将status赋值为未通过 #rejectResource
     *  4.通过之后，会将资源调用 #approveResource
     */
    @GetMapping("/resource_check")
    public String resourcecheck(Model model) {
        model.addAttribute("allResources",resourceService.findAllResources());
        return "resource_check";
    }
    @GetMapping("/reject-resource/{id}")
    public ResponseEntity<String> rejectResource(@PathVariable("id") Long id, HttpServletRequest request) {
            Long reviewBy = (Long) request.getSession().getAttribute("id");
            resourceService.rejectResource(id, reviewBy);
            return ResponseEntity.ok("资源不通过审核");
    }

    @GetMapping("/approve-resource/{id}/{tagName}/{languageName}/{resourceTitle}")
    public ResponseEntity<String> approveResource(@PathVariable("id") Long id,@PathVariable("tagName")String tagName,
                                                  @PathVariable("languageName")String languageName,@PathVariable("resourceTitle")String resourceTitle,
                                                              HttpServletRequest request) {
        Long reviewBy = (Long) request.getSession().getAttribute("id");

        resourceService.approveResource(id, reviewBy,tagName,languageName,resourceTitle);

        return ResponseEntity.ok("Resource approved successfully");
    }


}