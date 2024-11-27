package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Tag;
import java.util.List;

public interface TagMapper {
    /**
     * 目前支持的所有标签 --cpp java python rust c
     * @return
     */
    List<Tag> selectAllTags();


}
