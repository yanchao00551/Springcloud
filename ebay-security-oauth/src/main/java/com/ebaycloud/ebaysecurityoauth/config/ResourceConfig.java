package com.ebaycloud.ebaysecurityoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @PackageName:com.ebaycloud.ebaysecurityoauth.config
 * @ClassName:ResourceConfig
 * @Description:
 * @author: 悟空
 * @date: 2021/4/22 20:17
 * @email: 10947@163.com
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/register").permitAll();
        //由于所有接口默认会被资源服务器保护的，所以这个地方我们需要放行注册接口
    }
}
