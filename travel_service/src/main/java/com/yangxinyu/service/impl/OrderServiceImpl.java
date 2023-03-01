package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yangxinyu.constant.RedisMessageConstant;
import com.yangxinyu.dao.MemberDao;
import com.yangxinyu.dao.OrderDao;
import com.yangxinyu.dao.OrderSettingDao;
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
        if (orderSettingByOrderDate<=0){
            //日期不存在
            return false;
        }


        return false;
    }
}
