package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 按照语言查询前十条资源
 * 按照标签查询前十条资源
 * 按照语言和标签共同查找资源
 */
public interface ResourceMapper {
    List<Resource> selectTop10Resources();


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
                        @Param("resourceTitle")String resourceTitle,
                        @Param("resourceUrl")String resourceUrl,
                        @Param("resourceDescription")String resourceDescription);

    void insertResource2(Resource resource);

    Integer findTagLanguageId(@Param("tagName") String tagName, @Param("languageName") String languageName);

    List<Resource> selectResourceById(Long id);
}
