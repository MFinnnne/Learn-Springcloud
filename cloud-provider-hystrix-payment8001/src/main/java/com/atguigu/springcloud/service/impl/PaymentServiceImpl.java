package com.atguigu.springcloud.service.impl;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:46
 **/

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.dao.PaymentMapper;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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


    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfoTimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "thread_pool:" + Thread.currentThread().getName() + "payment time out" + id;
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "thread pool " + Thread.currentThread().getName() + " paymentInfoTimeoutHandler " + " id " + id;
    }

    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
                    @HystrixProperty(name="circuitBreaker.enable",value = "true"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),
            }
    )
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id can not less than zero");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号: " + uuid;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id 不能负数,请稍后再试";
    }
}
