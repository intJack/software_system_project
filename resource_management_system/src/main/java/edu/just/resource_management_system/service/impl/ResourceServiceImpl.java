package edu.just.resource_management_system.service.impl;

import edu.just.resource_management_system.mapper.LanguageMapper;
import edu.just.resource_management_system.mapper.ResourceMapper;
import edu.just.resource_management_system.mapper.TagMapper;
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
    @Autowired
    TagMapper tagMapper;
    @Autowired
    LanguageMapper languageMapper;
    @Autowired
    TagService tagService;
    @Autowired
    LanguageService languageService;

//    @Override
//    public void saveTempResource(Resource resource) {
//        resourceMapper.insertTempResource(resource);
//    }

    /**
     * 管理员审核用户上传资源
     * 1.通过前端 传来的资源id 来进行添加操作
     * 2.首先在tag_language表 查询有没有(tagName,languageName)这个组合的id
     *  1.有则获得该id 在添加到resource表后() 把tag_language_id设置该id
     *  2.没有则 新增id 在添加到resource表后 把tag_language_id设置该id
     *   然后去查找这个tagName是否在tag表从未出现过，是就添加，否就什么也不做
     *  同理对于language表也是同样的操作
     *
     */

    @Override
    public void approveResource(Long id, Long reviewBy,String tagName,String languageName,String resourceTitle) {
        resourceMapper.updateResourceStatus(id, "已通过", reviewBy);
        /**
         * 由于数据库的复杂性，插入资源相当麻烦需要进行一系列操作
         * 首先在tag_language表 查询有没有(tagName,languageName)这个组合的id
         */
        Long ids = findIdByTagNameAndLanguageName(tagName, languageName);
        /**
         * ids != null 说明存在(tagName,languageName) 无需在tag表和language表还有tag_language表 添加新的tagName和languageName
         * id == null 说明出现新的tagName或者languageName 分别去这个表 到底是tagName 还是languageName 新的？
         * 还是两个都是新的
         *
         */
        if(ids == null){
            String tagName1 = tagMapper.selectTagNameByTagName(tagName);
            if (tagName1 == null){
                tagService.addTagName(tagName);
            }
            String languageName1 = languageMapper.selectLanguageNameByLanguageName(languageName);
            if (languageName1 == null){
                languageService.addLanguageName(languageName);
            }
            resourceMapper.insertTagNameAndLanguageName(tagName,languageName);
        }
        //得到tag_resource_id
        Long id1 = resourceMapper.findIdByTagNameAndLanguageName(tagName, languageName);
        //插入到resource
        resourceMapper.insertResource(id1,resourceTitle);
    }

    @Override
    public Long findIdByTagNameAndLanguageName(String tagName,String languageName) {
        return resourceMapper.findIdByTagNameAndLanguageName(tagName,languageName);
    }

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

    @Override
    public List<Resource> findResourcesByIdsAndKeyword(String tagName,String languageName,
                                                       String keyword) {
        List<Long> ids = tagService.findIdByTagAndLanguage(tagName, languageName);
        return resourceMapper.selectResourcesByIdsAndKeyword(ids,keyword);
    }

    /**
     * 查询最新的前 10 条资源
     * @param ids 可选的标签或语言 ID 列表
     * @return 资源列表
     */
    public List<Resource> findTop10ByOrderByCreateAtDesc(List<Integer> ids) {
        return resourceMapper.selectTop10Resources(ids);
    }


    public int getTagLanguageId(String tagName, String languageName) {
        return resourceMapper.findTagLanguageId(tagName, languageName);
    }

    public void saveResource(Resource resource) {
        resourceMapper.insertResource2(resource);
    }

}
