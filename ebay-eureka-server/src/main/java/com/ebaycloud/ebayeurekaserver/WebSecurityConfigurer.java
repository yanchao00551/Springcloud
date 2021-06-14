package com.ebaycloud.ebayeurekaserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 由于在当前版本spring security自动开启了csrf，由于等会要
 * 创建的Eureka Client与Eureka Server端口不同产生了跨域问题，
 * 这里我们需要写一个配置类关闭csrf
 *
 * @PackageName:com.ebaycloud.ebayeurekaserver
 * @ClassName:WebSecurityConfigurer
 * @Description:
 * @author: 悟空
 * @date: 2021/4/15 17:24
 * @email: 10947@163.com
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();     //关闭跨域
        http.authorizeRequests()   //认证请求
                .anyRequest()      //对任何请求
                .authenticated()   //都需要认证
                .and()
                .httpBasic();      //使用Spring Security提供的登录界面
    }
}