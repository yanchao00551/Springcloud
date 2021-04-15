package com.ebaycloud.ebayzuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class EbayZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbayZuulGatewayApplication.class, args);
    }

}
