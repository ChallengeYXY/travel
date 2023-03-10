package com.yangxinyu.service;

import com.yangxinyu.entity.Member;
import com.yangxinyu.entity.Order;

import java.util.Map;

/**
 * @BelongsProject : travel
 * @BelongsPackage : com.yangxinyu.service
 * @Date : 2023/3/1 10:31
 * @Author : 星宇
 * @Description :
 */
public interface OrderService {

    /**
     * 验证码校验
     * @param telephone
     * @param validateCode
     * @return
     */
    boolean checkCode(String telephone,String validateCode);

    /**
     * 判断日期是否合理
     * 当前日期没有设置预约、当前日期已经约满
     * @param orderDate
     * @return
     */
    boolean checkOrderDate(String orderDate);

    /**
     * 添加订单信息回填主键
     * @param
     * @param order
     */
    Order addOrder(Member member, Order order);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    Map findOrderMsgById(Integer id);
}
