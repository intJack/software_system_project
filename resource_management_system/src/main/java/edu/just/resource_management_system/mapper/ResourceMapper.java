package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Resource;

import java.util.List;

/**
 * 按照语言查询前十条资源
 * 按照标签查询前十条资源
 * 按照语言和标签共同查找资源
 */
public interface ResourceMapper {
    /**
     * 无论是单独查询语言 还是标签 最后都需要执行这一条 SQL语句
     * @param tagLanguageIds
     * @return 资源的URL 和 title
     */
    List<Resource> selectTop10Resources(List<Long>tagLanguageIds);

    /**
     * 关键字查询资源
     * @param keyWord 关键字
     * @return 资源的URL 和 title
     */
    List<Resource> selectByKeyWord(String keyWord);

}
