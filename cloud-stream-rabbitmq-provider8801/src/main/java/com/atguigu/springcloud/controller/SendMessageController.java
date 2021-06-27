package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/6/27 23:16
 **/
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value="/sendMe")
    public String sendMessage(){
        return messageProvider.send();
    }
}
