package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/6/15 23:15
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixFeignService {
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<Payment>(505, "----paymentFallbackService fall back");
    }

    @Override
    public String paymentFeignTimeout() {
        return null;
    }

    @Override
    public String paymentHystrixOk(Integer id) {
        return "----paymentHystrixOk fall back payment info";
    }

    @Override
    public String paymentHystrixTimeOut(Integer id) {
        return "----paymentHystrixTimeOut fall back payment info time out";
    }
}
