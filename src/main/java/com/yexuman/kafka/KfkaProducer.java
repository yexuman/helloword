package com.yexuman.kafka;

/**
 * @author yexuman
 * @date 2019/11/1 17:41
 */

import com.alibaba.fastjson.JSON;
import com.yexuman.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@Component
public class KfkaProducer {

    private static Logger logger = LoggerFactory.getLogger(KfkaProducer.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    //发送消息方法
    public void send() {
        for (int i = 0; i < 5; i++) {
            Message message = new Message();
            message.setId(System.currentTimeMillis());
            message.setMsg(UUID.randomUUID().toString() + "---" + i);
            message.setSendTime(new Date());
            logger.info("发送消息 ----->>>>>  message = {}", JSON.toJSONString(message));
            kafkaTemplate.send("test", JSON.toJSONString(message));
        }
    }


}
