package edu.just.resource_management_system.service;

import edu.just.resource_management_system.pojo.Language;

import java.util.List;

public interface LanguageService {
    List<Language> findAllLanguages();
    List<Long>findIdByTagAndLanguage(String tagName,String languageName);
//    Long findTagNameByTagName()
    void addLanguageName(String languageName);
}
