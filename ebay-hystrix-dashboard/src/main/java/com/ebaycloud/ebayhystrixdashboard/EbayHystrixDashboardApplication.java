package com.ebaycloud.ebayhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class EbayHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbayHystrixDashboardApplication.class, args);
    }

}
