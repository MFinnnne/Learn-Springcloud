package com.atguigu.springcloud.service.impl;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:46
 **/

import com.atguigu.springcloud.dao.PaymentMapper;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;


    @Override
    public int create(Payment payment) {
        return paymentMapper.insertSelective(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }

    @Override
    public String paymentInfoOk(Integer id) {
        return "thread_pool:" + Thread.currentThread().getName() + "payment_ok" + id;
    }


    @Override
    public String paymentInfoTimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "thread_pool:" + Thread.currentThread().getName() + "payment time out" + id;
    }
}
