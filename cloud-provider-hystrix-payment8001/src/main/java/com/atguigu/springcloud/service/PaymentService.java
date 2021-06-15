package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:45
 **/
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById( Long id);


    String paymentInfoOk(Integer id);


    String paymentInfoTimeOut(Integer id);

    String paymentCircuitBreaker( Integer id);

}
