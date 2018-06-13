package com.learn.example.controller;

import com.learn.example.AppException;
import com.learn.example.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    private static Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/index")
    public String index() throws AppException {
        throw  new AppException("404","找不到请求资源");
    }

    @GetMapping("/findStaff")
    public String findStaff(@Validated Staff staff){
        return "";
    }
}
