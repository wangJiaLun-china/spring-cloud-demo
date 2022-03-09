package com.wjl.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangJiaLun
 * @date 2022-03-03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignConsumerAdvancedApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignConsumerAdvancedApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
