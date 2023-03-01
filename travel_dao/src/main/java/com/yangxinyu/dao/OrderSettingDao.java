package com.yangxinyu.dao;


import com.yangxinyu.entity.OrderSetting;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderSettingDao {
    /**
     * 向预约管理表中添加数据
     * 添加日期与数量
     * @param orderSetting
     */
    void addOrderSetting(int number, String orderSetting,int reservations);

    /**
     * 根据日期查询预约管理数
     * @param orderDate
     * @return 返回查询行数
     */
    public int getOrderSettingByOrderDate(String orderDate);

    /**
     * 根据日期修改预约管理
     * @param orderSetting
     */;
    void updateOrderSettingByOrderDate(int number, String orderSetting);

    /**
     * 根据日期区间查找预约表
     * @param start
     * @param end
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(String start, String end);

    /**
     * 通过日期修改可预约人数
     * @param date
     * @param num
     */
    void setNumberByDate(String date, Integer num);

    /**
     * 根据日期查询预约管理
     * @param orderDate
     * @return
     */
    OrderSetting getOrderSettingByOrderDate2(String orderDate);

    /**
     * 根据日期更新已经预约人数
     * @param orderDate
     * @param reservations
     */
    void updateReservationsByOrderDate(String orderDate, int reservations);
}
