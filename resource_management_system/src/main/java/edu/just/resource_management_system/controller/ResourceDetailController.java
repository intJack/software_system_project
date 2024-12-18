package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.pojo.ResourceRequest;
import edu.just.resource_management_system.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;

//@RestController
@Controller
@RequestMapping("/resources")
public class ResourceDetailController {

    @Autowired
    private ResourceService resourceService;

    @ResponseBody
    @PostMapping("/submit1")
    public String submitResource(@RequestBody ResourceRequest resourceRequest, HttpServletRequest request) {
        try {
            // 生成 txt 文件
            String fileName = resourceRequest.getResourceTitle().replaceAll("\\s+", "_") + ".txt";
            String filePath = "resources/" +request.getSession().getAttribute("userName")+"/" +fileName;
            File file = new File(filePath);

            // 确保文件夹存在
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//                writer.write("资源标题: " + resourceRequest.getResourceTitle() + "\n");
//                writer.write("标签: " + resourceRequest.getTagName() + "\n");
//                writer.write("语言: " + resourceRequest.getLanguageName() + "\n");
//                writer.write("资源描述: " + resourceRequest.getResourceDescription() + "\n");

                writer.write(resourceRequest.getResourceWords() + "\n");
            }

            // 调用服务层保存到数据库
            Resource resource = new Resource();
            resource.setResourceTitle(resourceRequest.getResourceTitle());
            //resource.setTagLanguageId((long) resourceService.getTagLanguageId(resourceRequest.getTagName(), resourceRequest.getLanguageName()));
            resource.setResourceUrl(filePath); // 保存文件路径到数据库
            resource.setResourceDescription(resourceRequest.getResourceDescription());
            resource.setTagName(resourceRequest.getTagName());
            resource.setLanguageName(resourceRequest.getLanguageName());
            resourceService.saveResource(resource);

            return "资源成功提交并生成文件: " + filePath;

        } catch (IOException e) {
            e.printStackTrace();
            return "生成文件失败: " + e.getMessage();
        }
    }
    @GetMapping("/details/{id}")
    public String getResourceContent(@PathVariable int id, Model model) {
        // 根据 ID 获取资源
        Resource resource = resourceService.findResourceById2(id);

        if (resource != null) {
            String filePath = resource.getResourceUrl();
            File file = new File(filePath);
            System.out.println(filePath);
            if (file.exists()) {
                try {
                    // 读取文件内容
                    StringBuilder content = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                            System.out.println("读取成功");
                        }
                    }

                    // 将文件内容添加到模型
                    model.addAttribute("resourceContent", content.toString());
                    model.addAttribute("resourceTitle", resource.getResourceTitle());
                    model.addAttribute("resourceDescription", resource.getResourceDescription());
                    model.addAttribute("resourceTagName", resource.getTagName());
                    return "details"; // 显示资源内容的页面

                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "文件读取失败: " + e.getMessage());
                }
            } else {
                model.addAttribute("error", "文件不存在");
            }
        } else {
            model.addAttribute("error", "资源不存在");
        }

        return "errorPage"; // 错误页面
    }


}
