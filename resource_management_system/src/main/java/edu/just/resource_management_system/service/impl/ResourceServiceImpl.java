package edu.just.resource_management_system.service.impl;

import edu.just.resource_management_system.mapper.ResourceMapper;
import edu.just.resource_management_system.pojo.Resource;
import edu.just.resource_management_system.service.LanguageService;
import edu.just.resource_management_system.service.ResourceService;
import edu.just.resource_management_system.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

//    @Override
//    public void saveTempResource(Resource resource) {
//        resourceMapper.insertTempResource(resource);
//    }
//
//    @Override
//    public void approveResource(Long id, Long reviewBy) {
//        resourceMapper.updateResourceStatus(id, "已通过", reviewBy);
//    }
//
    @Override
    public void rejectResource(Long id, Long reviewBy) {
        resourceMapper.updateResourceStatus(id, "已拒绝", reviewBy);
    }

    @Override
    public List<Resource> findAllResources() {
        return resourceMapper.selectAllResources();
    }

    /**
     * tag 和 language 都是为了更精确更迅速定位到 资源
     * tagService和languageService 的方法 都应该 通过 ResourceService的实现类去调用
     */
    @Autowired
    TagService tagService;
    @Autowired
    LanguageService languageService;
    @Override
    public List<Resource> findResourcesByIdsAndKeyword(String tagName,String languageName,
                                                       String keyword) {
        List<Long> ids = tagService.findIdByTagAndLanguage(tagName, languageName);
        return resourceMapper.selectResourcesByIdsAndKeyword(ids,keyword);
    }



}
