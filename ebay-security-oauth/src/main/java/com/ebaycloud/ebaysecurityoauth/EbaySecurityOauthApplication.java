package com.ebaycloud.ebaysecurityoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableDiscoveryClient
@SpringBootApplication
public class EbaySecurityOauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbaySecurityOauthApplication.class, args);
    }
}
