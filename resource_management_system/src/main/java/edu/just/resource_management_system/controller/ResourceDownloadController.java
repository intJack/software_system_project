package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/resources")
public class ResourceDownloadController {

    @Autowired
    private ResourceService resourceService;
    @GetMapping("/download/{id}")
    public void downloadResource(@PathVariable int id, HttpServletResponse response) {
        Resource resource = resourceService.findResourceById2(id);

        if (resource != null) {
            String filePath = resource.getResourceUrl();
            File file = new File(filePath);

            if (file.exists()) {
                try {
                    // 设置响应的内容类型为二进制流（下载文件）
                    response.setContentType("application/octet-stream");
                    response.setContentLengthLong(file.length());

                    // 对文件名进行编码，以支持中文或特殊字符
                    String encodedFileName = URLEncoder.encode(file.getName(), "UTF-8");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");

                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                         OutputStream outputStream = response.getOutputStream()) {

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    try {
                        response.getWriter().write("文件下载失败: " + e.getMessage());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                try {
                    response.getWriter().write("文件不存在");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().write("资源不存在");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
