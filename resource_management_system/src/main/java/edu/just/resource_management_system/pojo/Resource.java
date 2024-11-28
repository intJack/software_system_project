package edu.just.resource_management_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity  // 标注这是一个JPA实体类
@Data   // Lombok注解，自动生成 getter, setter, toString 等
@NoArgsConstructor  // Lombok注解，生成无参构造函数
@AllArgsConstructor // Lombok注解，生成全参构造函数
public class Resource {

    @Id  // 标注主键
    private Long id;  // 主键字段

    private String resourceTitle;
    private String resourceUrl;
    private String resourceDescription;

}
