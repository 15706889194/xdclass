package com.xdclass.couponapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DruidTest {
    @RequestMapping("test")
    public String test(){
        return "hello";
    }
}
