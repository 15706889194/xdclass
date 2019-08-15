package com.xdclass.couponapp;

import com.xdclass.couponapp.service.CouponService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
@State(Scope.Thread)
public class JMHSpringbootTest {
    private CouponService couponService;
    private  ConfigurableApplicationContext context;
    public static void main(String[] args) throws RunnerException {
        Options options=new OptionsBuilder().include(JMHSpringbootTest.class.getName()+".*").
                warmupIterations(2).measurementIterations(2) .forks(1).build();
        new Runner(options).run();
    }

    /**
     * 初始化  只加载一次
     */
    @Setup(Level.Trial)
    public void init(){
        String  args="";
        context = SpringApplication.run(CouponAppApplication.class, args);
        couponService =  context.getBean(CouponService.class);
    }
    @Benchmark
    public void testCouponService(){

        System.out.println(couponService.querrCoupon());
    }
}
