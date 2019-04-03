package com.naswork.erp.utils.rabbitMq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Program: FirstConsumer
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-13 22:26:01
 **/
@Component
public class FirstConsumer {

    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);
    }

}
