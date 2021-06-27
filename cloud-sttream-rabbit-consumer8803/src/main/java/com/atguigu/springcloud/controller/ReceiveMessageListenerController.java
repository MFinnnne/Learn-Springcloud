package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/6/27 23:16
 **/
@Component
@EnableBinding(Sink.class)
@Slf4j
public class ReceiveMessageListenerController {


    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("消费者二号,receive message: " + message.getPayload() + "\t port: " + serverPort);
    }

}
