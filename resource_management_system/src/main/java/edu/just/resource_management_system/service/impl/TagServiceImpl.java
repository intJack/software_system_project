package edu.just.resource_management_system.service.impl;

import edu.just.resource_management_system.mapper.TagMapper;
import edu.just.resource_management_system.pojo.Tag;
import edu.just.resource_management_system.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public List<Tag> findAllTags() {
        return tagMapper.selectAllTags();
    }

    @Override
    public List<Long> findIdByTagAndLanguage(String tagName, String languageName) {
        return tagMapper.selectIdByTagAndLanguage(tagName,languageName);
    }

    @Override
    public String findTagNameByTagName(String tagName) {
        return tagMapper.selectTagNameByTagName(tagName);
    }

    @Override
    public void addTagName(String tagName) {
        tagMapper.insertTagName(tagName);
    }
}
