package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:51
 **/
@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;


    @Value("${server.port}")
    private String servicePort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("结果: " + result);

        if (result > 0) {
            return new CommonResult<>(200, "success,server port:" + servicePort, result);

        }
        return new CommonResult<>(444, "filed", null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果：" + payment);

        if (payment != null) {
            //查询成功
            return new CommonResult<>(200, "查询成功,server port:" + servicePort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录，查询ID：" + id);
        }
    }

    @GetMapping("payment/discovery")
    public Object discovery() {
        List<String> services = this.discoveryClient.getServices();
        services.forEach((item) -> {
            log.info("*****element: " + item);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(item -> {
            log.info(item.getServiceId() + "\t" + item.getHost()
                    + "\t" + item.getPort() + "\t" + item.getUri());
        });
        return discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String myLb() {
        return servicePort;

    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servicePort;
    }

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentHystrixOk(@PathVariable Integer id) {
        return paymentService.paymentInfoOk(id);
    }

    @GetMapping(value = "/payment/hystrix/timeOut/{id}")
    public String paymentHystrixTimeOut(@PathVariable Integer id) {

        return paymentService.paymentInfoTimeOut(id);
    }


}
