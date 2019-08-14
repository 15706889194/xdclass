package com.xdclass.couponapp.config;

import org.springframework.beans.factory.DisposableBean;

public class TestDIsposeableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("测试已经销毁");
    }
}
