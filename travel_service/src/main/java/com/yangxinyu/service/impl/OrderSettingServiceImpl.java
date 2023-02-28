package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yangxinyu.dao.OrderSettingDao;
import com.yangxinyu.entity.OrderSetting;
import com.yangxinyu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    /**
     * 上传Excel
     * 将上解析后的Excel进行封装，传入dao层
     * @param excelRows
     */
    @Override
    public void upload(List<String[]> excelRows) throws ParseException {
        //创建一个OrderSetting对象
        OrderSetting orderSetting = new OrderSetting();
        //将每行封装为OrderSetting对象
        for (String[] excelRow : excelRows) {
            orderSetting.setOrderDate(new SimpleDateFormat("yyyy/MM/dd").parse(excelRow[0]));
            orderSetting.setNumber(Integer.parseInt(excelRow[1]));
            //判断当前日期是否存在
            int count = orderSettingDao.getOrderSettingByOrderDate(new SimpleDateFormat("yyyy-MM-dd").format(orderSetting.getOrderDate()));
            System.out.println(count);
            if (count>0){
                //当前日期存在，根据日期修改当前数量
                orderSettingDao.updateOrderSettingByOrderDate(orderSetting.getNumber(),new SimpleDateFormat("yyyy-MM-dd").format(orderSetting.getOrderDate()));
            }else {
                orderSettingDao.addOrderSetting(orderSetting.getNumber(),new SimpleDateFormat("yyyy-MM-dd").format(orderSetting.getOrderDate()),orderSetting.getReservations());
            }
        }
    }

    @Override
    public List<OrderSetting> getOrderSettingByMonth(Integer year,Integer month) {
        String start = year + "-" + month + "-" + 1;
        String end;
        //判断闰年与月份
        if((year%4 == 0 && year%100 != 0)||(year%400 == 0)){
            if(month==2){
                //闰年二月
                end = year + "-" + month + "-" + 29;
            }else if(month==1
                    ||month==3
                    ||month==5
                    ||month==7
                    ||month==8
                    ||month==10
                    ||month==12) {
                //闰年31天
                end = year + "-" + month + "-" + 31;
            }else {
                end = year + "-" + month + "-" + 30;
            }
        }else {
            if(month==2){
                //闰年二月
                end = year + "-" + month + "-" + 28;
            }else if(month==1
                    || month==3
                    || month==5
                    || month==7
                    || month==8
                    ||month==10
                    ||month==12) {
                //平年31天
                end = year + "-" + month + "-" + 31;
            }else {
                end = year + "-" + month + "-" + 30;
            }
        }

        List<OrderSetting> orderSettings = orderSettingDao.getOrderSettingByMonth(start,end);
        /*for (OrderSetting orderSetting : orderSettings) {
            System.out.println(orderSetting);;
        }*/
        return orderSettings;
    }

    @Override
    public void setNumberByDate(String orderDate, Integer num) {
        //判断当前日期是否存在
        int orderSettingByOrderDate = orderSettingDao.getOrderSettingByOrderDate(orderDate);
        if (orderSettingByOrderDate>0){
            //修改
            orderSettingDao.setNumberByDate(orderDate,num);
        }else {
            //添加
            orderSettingDao.addOrderSetting(num,orderDate,0);
        }
    }
}
