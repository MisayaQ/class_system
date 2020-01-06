package com.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 * 开启Eureka客户端
 * @author
 */
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.course.mapper")
public class ClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassApplication.class, args);
    }

}
