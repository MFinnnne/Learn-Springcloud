package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("结果: " + result);

        if (result > 0) {
            return new CommonResult<>(200, "success", result);

        }
        return new CommonResult<>(444, "filed", null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果：" + payment);

        if (payment != null) {
            //查询成功
            return new CommonResult<>(200, "查询成功", payment);
        } else {
            return new CommonResult<>(444, "没有对应记录，查询ID：" + id);
        }
    }
}
