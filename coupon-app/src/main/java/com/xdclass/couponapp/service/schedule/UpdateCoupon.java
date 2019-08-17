package com.xdclass.couponapp.service.schedule;

import com.xdclass.couponapp.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UpdateCoupon {
    private static final Logger logger= LoggerFactory.getLogger(UpdateCoupon.class);
    @Resource
    private CouponService couponService;
    @Scheduled(cron ="*/1 * * * *  ?")
    public void updateConpon(){
        couponService.updateCoupon();
        logger.info("update coupon service ");
    }
}
