package edu.just.resource_management_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
//import javax.persistence.Entity;
//import javax.persistence.Id;


@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private Long id;
    private String resourceTitle;
    private String resourceUrl;
    private String resourceDescription;
    private String tagName;
    private String languageName;
    private Long tagLanguageId;
    private Date createdAt;
    public Long getTagLanguageId() {
        return tagLanguageId;
    }

    public void setTagLanguageId(Long tagLanguageId) {
        this.tagLanguageId = tagLanguageId;
    }
}
