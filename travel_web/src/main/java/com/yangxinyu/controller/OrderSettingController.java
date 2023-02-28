package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.remoting.TimeoutException;
import com.alibaba.dubbo.rpc.RpcException;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.OrderSetting;
import com.yangxinyu.entity.Result;
import com.yangxinyu.poi.POIUtils;
import com.yangxinyu.service.OrderSettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 上传文件
     * @param excelFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            //将Excel解析为所有行的集合
            List<String[]> excelRows = POIUtils.readExcel(excelFile);

            orderSettingService.upload(excelRows);

            return new Result(true,MessageConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            return new Result(false,MessageConstant.UPLOAD_FAIL);
        } catch (ParseException e) {
            return new Result(false,MessageConstant.UPLOAD_FAIL);
        }
    }

    /**
     * 通过年月获取整个日历预定信息
     * @param year
     * @param month
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(Integer year,Integer month){
        List<OrderSetting> orderSettings = orderSettingService.getOrderSettingByMonth(year,month);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,orderSettings);
    }

    /**
     * 设置可预约人数
     * @param date
     * @return
     */
    @RequestMapping("/setNumber")
    public Result setNumber(String date,Integer num){
        try {
            orderSettingService.setNumberByDate(date,num);
            return new Result(true ,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
