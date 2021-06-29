package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/6/29 23:08
 **/
@RestController
public class EchoController {

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }
}