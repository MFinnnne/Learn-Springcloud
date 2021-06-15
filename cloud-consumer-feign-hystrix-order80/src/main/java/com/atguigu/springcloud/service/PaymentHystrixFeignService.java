package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：MFine
 * @description：TODO
 * @date ：2021/6/13 1:07
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentFallbackService.class)
public interface PaymentHystrixFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String paymentHystrixOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeOut/{id}")
    String paymentHystrixTimeOut(@PathVariable("id") Integer id);


}