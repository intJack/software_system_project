package edu.just.resource_management_system.service;

import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.mapper.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository; // 资源存储库，用于与数据库交互

    /**
     * 获取最新的N条资源
     * @param limit 查询的资源条数
     * @return 最新的资源列表
     */
    public List<Resource> getLatestResources(int limit) {
        return resourceRepository.findTopNLatestResources(limit);  // 调用存储库方法获取最新资源
    }

    /**
     * 根据语言查询相关资源
     * @param language 语言
     * @return 与语言相关的资源列表
     */
    public List<Resource> findResourcesByLanguage(String language) {
        return resourceRepository.findResourcesByLanguage(language);  // 调用存储库方法根据语言查询资源
    }

    /**
     * 根据语言、标签和关键字查询资源
     * @param language 语言（可选）
     * @param tags 标签（可选）
     * @param keyword 关键字（可选）
     * @return 符合查询条件的资源列表
     */
    public List<Resource> searchResources(String language, String tags, String keyword) {
        return resourceRepository.searchResources(language, tags, keyword);  // 调用存储库进行条件查询
    }
}

