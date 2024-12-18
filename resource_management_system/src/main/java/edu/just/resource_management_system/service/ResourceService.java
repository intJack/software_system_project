package edu.just.resource_management_system.service;


import edu.just.resource_management_system.pojo.Resource;

import java.util.List;

public interface ResourceService {
    /**
     * 根据language_tag和 资源关键字共同查询
     */
    List<Resource> findResourcesByIdsAndKeyword(String tagName,String languageName,
                                                String keyword);

//    void saveTempResource(Resource resource);
     void approveResource(Long id, Long reviewBy,String tagName,String languageName,String resourceTitle,
                          String resourceUrl,String resourceDescription);

    void rejectResource(Long id, Long reviewBy);

    List<Resource> findAllResources();

    Long findIdByTagNameAndLanguageName(String tagName,String languageName);

    List<Resource> findTop10ByOrderByCreateAtDesc();

    void saveResource(Resource resource);

    int getTagLanguageId(String tagName, String languageName);

    List<Resource> findTempResourceById(Long id);
    Resource findResourceById2(int id);
}
