package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/6/29 23:07
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaPaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaPaymentMain9002.class);
    }
}
