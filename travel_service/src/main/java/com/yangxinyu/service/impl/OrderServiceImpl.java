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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
    public Order addOrder(Member member, Order order) {
        Member member1 = memberDao.getMemberByIdCard(member);
        if (member1!=null){
            //获取人员id
            order.setMemberId(member1.getId());
            //查询订单是否存在
            Order order1 = orderDao.getOrderByMemberAndOrderDate(order.getMemberId(),new SimpleDateFormat("yyyy-MM-dd").format(order.getOrderDate()));

            System.out.println(order1);
            if (order1!=null){
                throw new RuntimeException("订单已存在！");
            }
            order.setOrderDate(new Date(order.getOrderDate().getTime()+86400000));
            orderDao.addOrder(order);
            return order;
        }
        return null;
    }

    @Override
    public Map findOrderMsgById(Integer id) {
        Map map = orderDao.findOrderMsgById(id);
        System.out.println(map.get("orderDate"));
        return map;
    }
}
