package edu.just.resource_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "edu.just.resource_management_system.mapper")
@SpringBootApplication
public class springbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(springbootApplication.class, args);
    }

}
