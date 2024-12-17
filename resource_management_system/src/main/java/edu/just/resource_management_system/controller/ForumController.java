package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {
    // 帖子详情页面
    @GetMapping("/post/{id}")
    public String forumPost(@PathVariable Long id, Model model) {
        // 模拟帖子详情：实际应用中可以从数据库中获取数据
        Post post = new Post(id, "帖子标题示例", "这是帖子内容的详细信息...");
        model.addAttribute("post", post);
        return "forumPost"; // 返回帖子详情页面模板
    }

    // 论坛版块页面
    @GetMapping("/section/{name}")
    public String forumSection(@PathVariable String name, Model model) {
        // 模拟版块内容
        model.addAttribute("sectionName", name);
        model.addAttribute("posts", List.of(
                new Post(1L, "关于" + name + "的帖子1", "帖子内容..."),
                new Post(2L, "关于" + name + "的帖子2", "帖子内容...")
        ));
        return "forumSection"; // 返回版块页面模板
    }
}
