package com.yangxinyu.service;

import com.yangxinyu.entity.OrderSetting;

import java.text.ParseException;
import java.util.List;

public interface OrderSettingService {
    /**
     * 上传Excel
     * 将上解析后的Excel进行封装，传入dao层
     * @param excelRows
     */
    public void upload(List<String[]> excelRows) throws ParseException;

    /**
     * 通过年月查询预约表
     * @param month
     * @return
     */

    List<OrderSetting> getOrderSettingByMonth(Integer year, Integer month);

    /**
     * 通过日期修改可预约人数
     * @param date
     * @param num
     */
    void setNumberByDate(String date, Integer num);
}
