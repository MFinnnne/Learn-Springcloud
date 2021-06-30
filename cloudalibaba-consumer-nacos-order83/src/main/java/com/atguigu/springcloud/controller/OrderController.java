package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/25 21:10
 **/
@RestController
@Slf4j
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("********插入的数据：" + payment);
        //postForObject分别有三个参数：请求地址，请求参数，返回的对象类型
        return restTemplate.postForObject(serviceUrl + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("********查询的id：" + id);
        //getForObject两个参数：请求地址，返回的对象类型
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(serviceUrl + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is4xxClientError()) {
            return new CommonResult<>(444, "操作失败");
        }
        return forEntity.getBody();
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances != null && instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
        return result;

    }

    @GetMapping("/consumer/payment/nacos")
    public String paymentNacos() {
        String result = restTemplate.getForObject(serviceUrl + "/payment/nacos/1", String.class);
        return result;
    }
}
