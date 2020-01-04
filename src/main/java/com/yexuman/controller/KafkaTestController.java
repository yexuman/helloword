package com.yexuman.controller;

import com.yexuman.kafka.KfkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yexuman
 * @date 2019/11/1 17:45
 */
@RestController
public class KafkaTestController {

    @Resource
    private KfkaProducer producer;

    @GetMapping("/testSendMsg")
    public String testSendMsg(){
        producer.send();
        return "success";
    }

}