package com.jingling.portalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-31 16:12
 * @Since: version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PortalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }

}
