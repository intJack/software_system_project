package edu.just.resource_management_system.controller;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.ResourceService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/resources")  // 定义基础请求路径 "/resources"。
public class ResourceDownloadController {

    @Autowired  // 自动注入 ResourceService，用于处理资源相关的业务逻辑。
    private ResourceService resourceService;

    // 处理下载请求，路径为 "/resources/download/{id}"，通过 GET 方法。
    @GetMapping("/download/{id}")
    public void downloadResource(@PathVariable int id, HttpServletResponse response) {
        // 根据资源ID从数据库中查询资源对象。
        Resource resource = resourceService.findResourceById2(id);

        // 如果资源存在，则继续处理下载。
        if (resource != null) {
            String filePath = resource.getResourceUrl();  // 获取资源的文件路径。
            File file = new File(filePath);  // 创建 File 对象，指向资源文件。

            // 检查文件是否存在。
            if (file.exists()) {
                try {
                    // 设置响应的内容类型为二进制流，表示该文件将被下载。
                    response.setContentType("application/octet-stream");
                    // 设置响应头中的文件长度，以便浏览器显示正确的进度条。
                    response.setContentLengthLong(file.length());

                    // 对文件名进行 URL 编码，确保中文或特殊字符能够正确显示。
                    String encodedFileName = URLEncoder.encode(file.getName(), "UTF-8");
                    // 设置 Content-Disposition 头部，指示浏览器下载该文件。
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");

                    // 使用缓冲流读取文件并写入响应流，实现文件下载。
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                         OutputStream outputStream = response.getOutputStream()) {

                        byte[] buffer = new byte[1024];  // 定义缓冲区，大小为1024字节。
                        int bytesRead;  // 记录每次读取的字节数。

                        // 循环读取文件并写入到输出流。
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);  // 将读取到的字节写入响应流。
                        }
                        outputStream.flush();  // 确保数据被完全写入输出流。
                    }
                } catch (IOException e) {
                    // 异常处理：如果出现 I/O 错误，返回服务器错误状态码并输出错误信息。
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    try {
                        response.getWriter().write("文件下载失败: " + e.getMessage());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();  // 如果响应输出失败，打印堆栈信息。
                    }
                }
            } else {
                // 如果文件不存在，返回 404 错误并提示文件不存在。
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                try {
                    response.getWriter().write("文件不存在");
                } catch (IOException e) {
                    e.printStackTrace();  // 输出异常信息。
                }
            }
        } else {
            // 如果资源不存在（即数据库中没有该 ID 的资源），返回 404 错误并提示资源不存在。
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().write("资源不存在");
            } catch (IOException e) {
                e.printStackTrace();  // 输出异常信息。
            }
        }
    }
}
