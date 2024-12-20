package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Language;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LanguageMapper {
    /**
     *  查询资源管理系统所支持的语言 --Chinese English Russian Japanese
     */
    List<Language> selectAllLanguages();

    /**
     * 根据语言查询的第一步：从tag_language库找到某个语言下的ids
     * 在ResourceMapper 实现根据ids来找到资源
     * @param language
     * @return
     */
    List<Long> selectIdsByLanguages(String language);

    List<Long> selectIdByTagAndLanguage(@Param("tagName") String tagName,
                                        @Param("languageName") String languageName);

    /**
     * 判断language表是否存在languageName
     * @param languageName
     * @return
     */
    String selectLanguageNameByLanguageName(String languageName);

    void insertLanguageName(String languageName);
}
