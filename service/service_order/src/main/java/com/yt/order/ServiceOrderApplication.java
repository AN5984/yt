package com.yt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zjd
 * @create 2021-08-23 19:51
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yt"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.yt"})
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}