package com.atguigu.service.impl;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:46
 **/

import com.atguigu.dao.PaymentMapper;
import com.atguigu.entities.Payment;
import com.atguigu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
