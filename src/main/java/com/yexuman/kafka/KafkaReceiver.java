package com.yexuman.kafka;

/**
 * @author yexuman
 * @date 2019/11/1 17:44
 */

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {

    private static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);


    @KafkaListener(topics = {"test"}, containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object o = kafkaMessage.get();
            String s1 = String.format("-------消费到了记录---------- record = %s", record);
            logger.info(s1);
            logger.info(String.format("-------消息内容----------- message = %s", o));
        }

    }


}
