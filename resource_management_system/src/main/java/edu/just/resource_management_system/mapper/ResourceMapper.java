package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 按照语言查询前十条资源
 * 按照标签查询前十条资源
 * 按照语言和标签共同查找资源
 */
public interface ResourceMapper {
    List<Resource>selectTop10Resources(List<Long>ids);

    List<Resource>selectResourcesByIdsAndKeyword(@Param("ids") List<Long>ids,
                                                 @Param("keyword") String keyword);

    // 插入待审核资源
    @Insert("INSERT INTO resources_temp(user_id, file_path, upload_time, status, review_time, review_by) " +
            "VALUES(#{userId}, #{filePath}, NOW(), '待审核', NULL, NULL)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTempResource(Resource resource);

    // 更新资源审核状态
    @Update("UPDATE resources_temp SET status = #{status}, review_time = NOW(), review_by = #{reviewBy} WHERE id = #{id}")
    void updateResourceStatus(@Param("id") Long id, @Param("status") String status, @Param("reviewBy") Long reviewBy);
}
