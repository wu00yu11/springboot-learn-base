package com.learn.example.controller;

import com.learn.example.AppException;
import com.learn.example.Result;
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
    private Result index() {
        Result result = new Result();
        try {
            helloWorldController.index();
        } catch (AppException e) {
            BeanUtils.copyProperties(e, result);
        }
        return result;
    }
}
