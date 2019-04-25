package com.naswork.erp.controller;

import com.naswork.erp.common.Result;
import com.naswork.erp.utils.annotation.Resource;
import com.naswork.erp.utils.redis.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: TestController
 * @Description:
 * @Author: White
 * @DateTime: 2019-04-18 10:35:59
 **/
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private JedisUtil jedisUtil;

    @GetMapping("/test1/{type}")
    @Resource(value = {"user","order"})
    public Result test1(@PathVariable String type){
        jedisUtil.STRINGS.set(type,type);
        return Result.requestBySuccess("tets1成功");
    }



}

