package com.naswork.erp.utils.rabbitMq.controller;

import com.naswork.erp.utils.rabbitMq.sender.FirstSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Program: SendController
 * @Description:
 * @Author: White
 * @DateTime: 2019-02-13 22:26:38
 **/
@RestController
public class SendController {

    @Autowired
    private FirstSender firstSender;

    @GetMapping("/send")
    public String send(String message){
        String uuid = UUID.randomUUID().toString();
        firstSender.send(uuid,message);
        return uuid;
    }

}
