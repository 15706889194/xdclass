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
    private  TCouponMapper tCouponMapper;

    @Resource
    private CouponService couponService;


    @RequestMapping("/test")
    public List select(){
        // select * from t_coupon where code = "00415d96-49bd-4cce-83e3-08302b9aa084" and status=0 and achieve_amount between (100,1000) and title not like '%111%';
        TCouponExample example = new TCouponExample();
        example.createCriteria().andCodeEqualTo("02bcf32c-6222-4055-b54a-c23d64a1cd44").andStatusEqualTo(0).andAchieveAmountBetween(100,1000).andTitleNotLike("111");
        List<TCoupon> tCoupon =  tCouponMapper.selectByExample(example);
       return tCoupon;
    }


    @RequestMapping("/test1")
    public String testQuery(String id){
        if(id==null){
            return "";
        }
        return couponService.query();
    }

}
