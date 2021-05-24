package com.ice.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全认证配置
 *
 * @author : Ice-winters
 * @since : 2021-05-24 20:24
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http); // 这里为了访问eureka控制台和 /actuator时做安全控制
        http.csrf().ignoringAntMatchers("/eureka/**"); // 或略 /eureka/** 的所有请求

    }
}
