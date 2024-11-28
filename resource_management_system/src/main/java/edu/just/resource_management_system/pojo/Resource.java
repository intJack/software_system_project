package edu.just.resource_management_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private String resourceTitle;
    private String resourceUrl;
    private String resourceDescription;

}