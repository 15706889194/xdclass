package com.xdclass.couponapp.service.schedule;

import com.xdclass.couponapp.service.CouponService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UpdateCoupon {
    @Resource
    private CouponService couponService;
    @Scheduled(cron ="*/1 * * * *  ?")
    public void updateConpon(){
        couponService.updateCoupon();
        System.out.println("update coupon service ");
    }
}
