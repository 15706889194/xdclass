package com.xdclass.couponapp.service;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xdclass.couponapp.domain.TCoupon;
import com.xdclass.couponapp.domain.TCouponExample;
import com.xdclass.couponapp.extances.Extances;
import com.xdclass.couponapp.mapper.TCouponMapper;
import com.xdclass.userapi.service.IUserService;
import io.netty.handler.codec.dns.TcpDnsQueryEncoder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class CouponService {

    @Resource
    private TCouponMapper tCouponMapper;


    @Reference
    private IUserService iUserService;


    /**
     * 缓存操作
     */
    LoadingCache<Integer, List<TCoupon>> couponCach=  CacheBuilder.newBuilder()
            //设置过期时间
            .expireAfterWrite(10, TimeUnit.MINUTES)
            //刷新时间
            .refreshAfterWrite(5,TimeUnit.MINUTES)
            .build(new CacheLoader<Integer, List<TCoupon>>() {
                @Override
                public List<TCoupon> load(Integer o) throws Exception {
                    return loadCoupon(o);
                }


            });
    private List<TCoupon> loadCoupon(Integer o) {
        TCouponExample tCouponExample = new TCouponExample();
        TCouponExample.Criteria criteria = tCouponExample.createCriteria();
        criteria.andStatusEqualTo(Extances.USEFULL).andEndTimeGreaterThan(new Date()).andStartTimeLessThan(new Date());
        return tCouponMapper.selectByExample(tCouponExample);
    }




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
        List<TCoupon> tCoupons=new ArrayList<TCoupon>();
        try {
             tCoupons = couponCach.get(1);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  tCoupons;
    }



}
