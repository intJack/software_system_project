package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Language;

import java.util.List;

public interface LanguageMapper {
    /**
     *  查询资源管理系统所支持的语言 --Chinese English Russian Japanese
     */
    List<Language> selectAllLanguages();

}
