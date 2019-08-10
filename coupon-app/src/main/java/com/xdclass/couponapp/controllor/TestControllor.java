package com.xdclass.couponapp.controllor;

import com.xdclass.couponapp.domain.TCoupon;
import com.xdclass.couponapp.domain.TCouponExample;
import com.xdclass.couponapp.mapper.TCouponMapper;
import com.xdclass.couponapp.service.CouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestControllor {


    @Resource
    private CouponService couponService;


    @RequestMapping("/test")
    public String test(){
        return "111";
    }

    @RequestMapping("/test1")
    public String testQuery(String id){
         if(id==null){
             return "";
         }
         return couponService.query();
    }

}
