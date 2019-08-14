package com.xdclass.couponapp.service;

import com.xdclass.couponapp.domain.TCoupon;
import com.xdclass.couponapp.domain.TCouponExample;
import com.xdclass.couponapp.extances.Extances;
import com.xdclass.couponapp.mapper.TCouponMapper;
import com.xdclass.userapi.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CouponService {

    @Resource
    private TCouponMapper tCouponMapper;


    @Reference
    private IUserService iUserService;

    public void print(){
        System.err.println("enter coupon service");
    }


    public String query(){
        TCouponExample example = new TCouponExample();
        example.createCriteria().andCodeEqualTo("0057da3c-f2ad-42bd-b6d2-8bb58b6dbc90");
        List<TCoupon> tCoupon =  tCouponMapper.selectByExample(example);
        return tCoupon.get(0).toString();
    }


    public String getUserById(int id){
        System.err.print(iUserService.getUserById(id));
        return iUserService.getUserById(id).toString();
    }

    public List<TCoupon> querrCoupon(){
        TCouponExample tCouponExample = new TCouponExample();
        TCouponExample.Criteria criteria = tCouponExample.createCriteria();
        criteria.andStatusEqualTo(Extances.USEFULL).andEndTimeGreaterThan(new Date()).andStartTimeLessThan(new Date());
        return tCouponMapper.selectByExample(tCouponExample);
    }



}
