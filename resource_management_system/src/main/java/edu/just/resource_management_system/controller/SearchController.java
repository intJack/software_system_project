package edu.just.resource_management_system.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ResourceService resourceService; // 注入资源服务类，用于获取资源数据

    /**
     * 显示最新的10条资源。
     * @param model 用于传递数据到视图
     * @return 返回主页视图，展示最新的10条资源
     */
    @RequestMapping("/home")
    public String showLatestResources(Model model) {
        // 获取最新的10条资源
        List<Resource> latestResources = resourceService.getLatestResources(10);
        // 将最新的资源列表传递给视图
        model.addAttribute("latestResources", latestResources);
        // 返回主页视图
        return "home"; // 主页视图中展示最新资源的标题
    }

    /**
     * 根据编程语言查询相关的资源。
     * @param language 语言名称
     * @param model 用于传递数据到视图
     * @return 返回搜索结果页面，展示与语言相关的资源
     */
    @RequestMapping("/search/{language}")
    public String searchByLanguage(@PathVariable("language") String language, Model model) {
        // 根据语言查询相关资源
        List<Resource> resources = resourceService.findResourcesByLanguage(language);
        // 将查询到的资源列表传递给视图
        model.addAttribute("resources", resources);
        model.addAttribute("language", language);
        // 返回查询结果页面
        return "searchResults"; // 需要创建该视图，展示语言相关的资源
    }

    /**
     * 根据条件（语言、标签、关键字）查询资源。
     * @param language 语言（可选）
     * @param tags 标签（可选）
     * @param keyword 关键字（可选）
     * @param model 用于传递数据到视图
     * @return 返回搜索结果页面，展示符合条件的资源列表
     */
    @RequestMapping("/search")
    public String searchResources(
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        // 调用资源服务类中的方法，根据条件（语言、标签、关键字）进行查询
        List<Resource> resources = resourceService.searchResources(language, tags, keyword);
        // 将查询到的资源列表传递给视图
        model.addAttribute("resources", resources);
        model.addAttribute("language", language);
        model.addAttribute("tags", tags);
        model.addAttribute("keyword", keyword);

        // 返回查询结果页面
        return "searchResults"; // 需要创建该视图，展示查询结果
    }
}
