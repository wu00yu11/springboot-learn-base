package com.learn.example.controller;

import com.learn.example.AppException;
import com.learn.example.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("springlearn")
public class RequestGateway {
    @Autowired
    private HelloWorldController helloWorldController;

    @RequestMapping("/index")
    private ResultData index() {
        ResultData resultData = new ResultData();
        try {
            helloWorldController.index();
        } catch (AppException e) {
            BeanUtils.copyProperties(e,resultData);
        }
        return resultData;
    }
}
