package edu.just.resource_management_system.pojo;


import lombok.*;
import org.springframework.stereotype.Repository;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    private String languageName;
    private String description;
}
