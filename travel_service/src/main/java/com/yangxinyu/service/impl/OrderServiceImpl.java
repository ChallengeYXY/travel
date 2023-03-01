package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yangxinyu.constant.RedisMessageConstant;
import com.yangxinyu.dao.MemberDao;
import com.yangxinyu.dao.OrderDao;
import com.yangxinyu.dao.OrderSettingDao;
import com.yangxinyu.entity.Member;
import com.yangxinyu.entity.Order;
import com.yangxinyu.entity.OrderSetting;
import com.yangxinyu.qiniu.RedisConstant;
import com.yangxinyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @BelongsProject : travel
 * @BelongsPackage : com.yangxinyu.service.impl
 * @Date : 2023/3/1 10:38
 * @Author : 星宇
 * @Description :
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean checkCode(String telephone,String validateCode) {
        //拿取验证码
        Jedis resource = jedisPool.getResource();
        resource.select(1);
        String code = resource.get(RedisMessageConstant.SENDTYPE_ORDER + telephone);
        if (code==null||!validateCode.equals(code)){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkOrderDate(String orderDate) {

        //查询预约日期
        int orderSettingByOrderDate = orderSettingDao.getOrderSettingByOrderDate(orderDate);
        System.out.println(orderSettingByOrderDate);
        if (orderSettingByOrderDate<=0){
            //日期不存在
            return false;
        }
        //查询预约情况
        OrderSetting orderSetting = orderSettingDao.getOrderSettingByOrderDate2(orderDate);
        if (orderSetting.getNumber()<=orderSetting.getReservations()){
            //已经预约满了
            return false;
        }
        //可以正常预约
        orderSettingDao.updateReservationsByOrderDate(orderDate,orderSetting.getReservations()+1);

        return true;
    }

    @Override
    public void addOrder(Member member, Order order) {
        Member member1 = memberDao.getMemberByIdCard(member);
        if (member1!=null){
            order.setMemberId(member1.getId());
            orderDao.addOrder(order);
        }
    }
}
