package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping("/payment/zk/get/{id}")
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
}
