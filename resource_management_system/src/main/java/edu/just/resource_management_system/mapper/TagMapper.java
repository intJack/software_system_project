package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {
    /**
     * 目前支持的所有标签 --cpp java python rust c
     * @return
     */
    List<Tag> selectAllTags();

    /**
     * 和查询language一样
     * 第一步要在 tag_language找到大量ids
     * @param tagName
     * @return
     */
    List<Long> selectIdsByTags(String tagName);

    List<Long> selectIdByTagAndLanguage(@Param("tagName") String tagName,
                                        @Param("languageName") String languageName);
}
