package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/25 21:06
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaNacosConsumerOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaNacosConsumerOrderMain83.class, args);
    }


}