package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentHystrixFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/25 21:10
 **/
@RestController
@Slf4j
public class OrderHystrixFeignController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private PaymentHystrixFeignService paymentHystrixFeignService;


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("********查询的id：" + id);
        return paymentHystrixFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentHystrixFeignService.paymentFeignTimeout();
    }

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        return paymentHystrixFeignService.paymentHystrixOk(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixFeignService.paymentHystrixTimeOut(id);
    }
}
