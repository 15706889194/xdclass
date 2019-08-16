package com.xdclass.couponapp.service;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.xdclass.couponapp.domain.TCoupon;
import com.xdclass.couponapp.domain.TCouponExample;
import com.xdclass.couponapp.extances.Extances;
import com.xdclass.couponapp.mapper.TCouponMapper;
import com.xdclass.userapi.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CouponService {

    @Resource
    private TCouponMapper tCouponMapper;


    @Reference
    private IUserService iUserService;


    /**
     * 缓存操作  缓存所有的
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
    Map map = new ConcurrentHashMap<>();
    public void  updateCoupon(){
        Map couponMap = new ConcurrentHashMap<>();
        List<TCoupon> tCoupons = this.loadCoupon(1);
        couponMap.put(1,tCoupons);
        map=couponMap;
    }





    private List<TCoupon> loadCoupon(Integer o) {
        TCouponExample tCouponExample = new TCouponExample();
        TCouponExample.Criteria criteria = tCouponExample.createCriteria();
        criteria.andStatusEqualTo(Extances.USEFULL).andEndTimeGreaterThan(new Date()).andStartTimeLessThan(new Date());
        return tCouponMapper.selectByExample(tCouponExample);
    }



    /**
     * 缓存操作  缓存批量id的
     */
    LoadingCache<Integer, TCoupon> coupons=CacheBuilder.newBuilder()
            .expireAfterWrite(10,TimeUnit.MINUTES)
            .refreshAfterWrite(10,TimeUnit.MINUTES)
            .build(new CacheLoader<Integer, TCoupon>() {
                @Override
                public TCoupon load(Integer o) throws Exception {
                    return loadCouponid(o);
                }
            });

    /**
     * 从数据库中获取单条数据时加入缓存
     * @param id
     * @return
     */
    private TCoupon loadCouponid(Integer id) {
        TCoupon tCoupon = tCouponMapper.selectByPrimaryKey(id);
        return tCoupon;
    }
    /**
     * 从缓存中获取多条数据
     * @param ids
     * @return
     */

    private List<TCoupon> getCouponListByIds(String  ids) {
        List<String> listId = Lists.newArrayList(ids.split(","));
        //需要到数据库中查询的id数组
        List<Integer> loadFromDB = Lists.newArrayList();
        //所需要结果集
        List<TCoupon> tCoupons = Lists.newArrayList();
        //数据库查询到的结果集
        List<TCoupon> couponFromDB=Lists.newArrayList();
        for (String s:listId) {
            TCoupon tCoupon = coupons.getIfPresent(1);
            //缓存为空
            if(tCoupon==null){
                loadFromDB.add(Integer.parseInt(s));
            }else {
                tCoupons.add(tCoupon);
            }
        }
        couponFromDB=getCouponFromDB(loadFromDB);
        tCoupons.addAll(couponFromDB);
        //数据加缓存
        Map<Integer, TCoupon> maps = couponFromDB.stream().collect(Collectors.toMap(TCoupon::getId, Tcoupon->Tcoupon));
        coupons.putAll(maps);
        return tCoupons;


    }
    /*
      根据ids查询记录
     */
    public List<TCoupon> getCouponFromDB(List<Integer> ids){
        TCouponExample example = new TCouponExample();
        example.createCriteria().andIdIn(ids);
        return tCouponMapper.selectByExample(example);
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

    /**
     * 跨服务访问
     * @param id
     * @return
     */
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
