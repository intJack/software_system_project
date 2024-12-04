package edu.just.resource_management_system.service.impl;

import edu.just.resource_management_system.mapper.LanguageMapper;
import edu.just.resource_management_system.pojo.Language;
import edu.just.resource_management_system.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("languageService")
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    LanguageMapper languageMapper;
    @Override
    public List<Language> findAllLanguages() {
        return languageMapper.selectAllLanguages();
    }

    @Override
    public List<Long> findIdByTagAndLanguage(String tagName, String languageName) {
        return languageMapper.selectIdByTagAndLanguage(tagName,languageName);
    }

    @Override
    public void addLanguageName(String languageName) {
        languageMapper.insertLanguageName(languageName);
    }
}
