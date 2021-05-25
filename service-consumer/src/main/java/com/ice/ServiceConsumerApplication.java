package com.ice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Ice-winters
 * @since : 2021-05-23 23:42
 */
@SpringBootApplication
public class ServiceConsumerApplication {

    @Bean
//    @LoadBalanced // 负载均衡注解
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


//    @Bean // 全局设置 负载均衡随机策略
//    public RandomRule randomRule() {
//        return new RandomRule();
//    }


    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class);
    }
}
