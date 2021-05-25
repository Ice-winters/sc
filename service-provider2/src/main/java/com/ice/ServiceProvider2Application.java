package com.ice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author Ice-winters
 */
@SpringBootApplication
// 开启eureka client注解， 目前版本如果配置中添加了Eureka注册中心，默认会开启该注解
//@EnableDiscoveryClient
public class ServiceProvider2Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider2Application.class);
    }
}
