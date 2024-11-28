package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    /**
     * 获取最新的N条资源
     * @param limit 查询的资源条数
     * @return 最新的资源列表
     */
    @Query("SELECT r FROM Resource r ORDER BY r.createdAt DESC")
    List<Resource> findTopNLatestResources(int limit);

    /**
     * 根据语言查询资源
     * @param language 语言
     * @return 与语言相关的资源列表
     */
    List<Resource> findResourcesByLanguage(String language);

    /**
     * 根据条件查询资源：语言、标签、关键字
     * @param language 语言（可选）
     * @param tags 标签（可选）
     * @param keyword 关键字（可选）
     * @return 符合条件的资源列表
     */
    @Query("SELECT r FROM Resource r WHERE " +
            "(r.language LIKE %:language% OR :language IS NULL) AND " +
            "(r.tags LIKE %:tags% OR :tags IS NULL) AND " +
            "(r.title LIKE %:keyword% OR r.description LIKE %:keyword% OR :keyword IS NULL)")
    List<Resource> searchResources(String language, String tags, String keyword);
}

