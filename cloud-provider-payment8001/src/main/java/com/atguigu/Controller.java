package com.atguigu;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import com.atguigu.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/24 23:51
 **/
@RestController
@Slf4j
public class Controller {

    @Resource
    private PaymentService paymentService;

    public CommonResult<Integer> create(Payment payment) {
        int result = paymentService.create(payment);
        log.info("结果: " + result);

        if (result > 0) {
            return new CommonResult<>(200, "success", result);

        }
        return new CommonResult<>(444, "filed", null);
    }
}
