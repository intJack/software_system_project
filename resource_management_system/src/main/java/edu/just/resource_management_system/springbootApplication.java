package edu.just.resource_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "edu.just.resource_management_system.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class springbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(springbootApplication.class, args);
    }

}
