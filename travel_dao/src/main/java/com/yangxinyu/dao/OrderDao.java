package com.yangxinyu.dao;

import com.yangxinyu.entity.Order;

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
}
