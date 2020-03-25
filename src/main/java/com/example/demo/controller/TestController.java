package com.example.demo.controller;

import com.example.demo.config.WeChatConfig;
import com.example.demo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private WeChatConfig weChatConfig;

    @RequestMapping("/test_config")
    public String testConfig(){
        return weChatConfig.getAppId();
    }

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("/test_db")
    public Object testDB(){
        return videoMapper.findAll();
    }
}
