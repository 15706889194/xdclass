package com.xdclass.couponapp.controller;

import com.xdclass.couponapp.domain.TCoupon;
import com.xdclass.couponapp.service.CouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class TestControllor {

    @Resource
    private CouponService couponService;

    @RequestMapping("/test")
    public String test(Integer id){

        return couponService.getUserById(id);
    }
}
