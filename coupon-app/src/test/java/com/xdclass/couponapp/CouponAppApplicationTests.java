package com.xdclass.couponapp;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.xdclass.couponapp.domain.TCouponExample;
import com.xdclass.couponapp.service.CouponService;
import com.xdclass.couponapp.domain.TCoupon;
import com.xdclass.couponapp.mapper.TCouponMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponAppApplication.class)
public class CouponAppApplicationTests {


    @Resource
    private CouponService couponService;

    @Resource
    private TCouponMapper tCouponMapper;

    @Test
    public void contextLoads() {
        couponService.print();
        System.err.println("hello world");
    }

    @Test
    public void insert(){
        for(int i=0;i<100000;i++){
            TCoupon tCoupon = new TCoupon();
            tCoupon.setAchieveAmount(500);
            tCoupon.setReduceAmount(20);
            tCoupon.setCreatetime(new Date());
            tCoupon.setCode(UUID.randomUUID().toString());
            tCoupon.setPicUrl("1.jpg");
            tCoupon.setStatus(0);
            tCoupon.setStartTime(new Date());
            tCoupon.setEndTime(new Date());
            //tCoupon.setStock(100L);
            tCoupon.setTitle("测试coupon");
            tCouponMapper.insert(tCoupon);

        }

    }

    @Test
    public void delete(){

        tCouponMapper.deleteByPrimaryKey(7);
    }

    @Test
    public void update(){
        TCoupon tCoupon = new TCoupon();
        tCoupon.setId(8);
        tCoupon.setCode("9527");
        tCouponMapper.updateByPrimaryKeySelective(tCoupon);
        tCouponMapper.updateByPrimaryKey(tCoupon);
    }


    @Test
    public void select(){
        // select * from t_coupon where code = "00415d96-49bd-4cce-83e3-08302b9aa084" and status=0 and achieve_amount between (100,1000) and title not like '%111%';
        TCouponExample example = new TCouponExample();
        example.createCriteria().andCodeEqualTo("704547df-4ed5-4278-a092-1b04b4305b41");
//                .andStatusEqualTo(0)
//                .andAchieveAmountBetween(100,1000).andTitleNotLike("111");
        List<TCoupon> tCoupon =  tCouponMapper.selectByExample(example);
        System.err.println(tCoupon);
    }


    @Test
    public void  test(){
        System.out.println("test");
        List<TCoupon> tCoupons = couponService.querrCoupon();
        System.out.println(tCoupons.toString());
    }




}
