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
    List<Resource>selectAllResources();


    void updateResourceStatus(@Param("id") Long id, @Param("status") String status, @Param("reviewBy") Long reviewBy);


    /**
     * 找到指定(tagName,languageName)对应的id
     */
    Long findIdByTagNameAndLanguageName(@Param("tagName")String tagName,
                                        @Param("languageName")String languageName);
    /**
     * 插入指定(tagName,languageName)
     */
    void insertTagNameAndLanguageName(@Param("tagName")String tagName,@Param("languageName")String languageName);

    /**
     * 修改指定id资源的tagLanguageId
     * @param tagLanguageId
     * @param id
     */
    void UpdateResourceByTagLanguageId(Long tagLanguageId,Long id);

    void insertResource(@Param("tagLanguageId")Long tagLanguageId,
                        @Param("resourceTitle")String resourceTitle);
//     插入待审核资源
//    @Insert("INSERT INTO resources_temp(user_id, file_path, upload_time, status, review_time, review_by) " +
//            "VALUES(#{userId}, #{filePath}, NOW(), '待审核', NULL, NULL)")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insertTempResource(Resource resource);
//
//    @Insert("INSERT INTO resource (id, resource_title, resource_url, resource_description)" +
//            " VALUES (#{id}, #{resourceTitle}, #{resourceUrl}, #{resourceDescription})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insertResource(Resource resource);



}
