package edu.just.resource_management_system.service;


import edu.just.resource_management_system.pojo.Resource;

import java.util.List;

public interface ResourceService {
    /**
     * 根据language_tag和 资源关键字共同查询
     */
    List<Resource> findResourcesByIdsAndKeyword(String tagName,String languageName,
                                                String keyword);
//    void saveResource(Resource resource);
}
