package com.yangxinyu.dao;

import com.yangxinyu.entity.Order;

import java.util.Map;

/**
 * @BelongsProject : travel
 * @BelongsPackage : com.yangxinyu.dao
 * @Date : 2023/3/1 10:47
 * @Author : 星宇
 * @Description :
 */
public interface OrderDao {
    /**
     * 添加订单信息
     * @param order
     */
    void addOrder(Order order);

    /**
     * 根据人员编号、预定日期、预定套餐名查询订单
     * @param
     * @return
     */
    Order getOrderByMemberAndOrderDateAndSetmealId(Integer memberId, String format, Integer setmealId);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    Map findOrderMsgById(Integer id);


}
