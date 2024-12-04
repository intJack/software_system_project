package edu.just.resource_management_system.service;

import edu.just.resource_management_system.pojo.Tag;

import java.util.List;

public interface TagService {
    List<Tag>findAllTags();
    List<Long>findIdByTagAndLanguage(String tagName,String languageName);
    String findTagNameByTagName(String tagName);
    void addTagName(String tagName);
}
