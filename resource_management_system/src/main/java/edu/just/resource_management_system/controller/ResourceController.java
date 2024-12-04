package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    // 省略其他方法...

    @PostMapping("/save-temp-resource")
    public ResponseEntity<String> saveTempResource(@RequestBody Resource resource) {
        resourceService.saveTempResource(resource);
        return ResponseEntity.ok("Resource saved for review");
    }

    @PostMapping("/approve-resource/{id}")
    public ResponseEntity<String> approveResource(@PathVariable Long id, @RequestBody Long reviewBy) {
        resourceService.approveResource(id, reviewBy);
        return ResponseEntity.ok("Resource approved successfully");
    }

    @PostMapping("/reject-resource/{id}")
    public ResponseEntity<String> rejectResource(@PathVariable Long id, @RequestBody Long reviewBy) {
        resourceService.rejectResource(id, reviewBy);
        return ResponseEntity.ok("Resource rejected successfully");
    }
}