package com.ebaycloud.ebayconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class EbayConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbayConfigServerApplication.class, args);
    }

}
