package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.Result;
import com.yangxinyu.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @BelongsProject : travel
 * @BelongsPackage : com.yangxinyu.controller
 * @Date : 2023/3/1 10:16
 * @Author : 星宇
 * @Description :
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    /**
     *
     * @param map
     * orderInfo:{
     *            name:null,//姓名
     *            setmealId:id,
     *            sex:'1',
     *            telephone:null,//手机号
     *            orderDate:null,//日期
     *            idCard:null,//身份证号
     *            validateCode:null//验证码
     * }//预约信息
     * @return
     */
    @RequestMapping("/saveOrder")
    public Result saveOrder(@RequestBody Map<String,String> map){
        //用户信息处理
        String name = map.get("name");
        String sex = map.get("sex");

        //校验验证码
        String telephone = map.get("telephone");
        String validateCode = map.get("validateCode");
        boolean codeFlag = orderService.checkCode(telephone,validateCode);
        if (!codeFlag){
            //验证码有误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        //判断预约日期是否合理，进行预约设置修改
        String orderDate = map.get("orderDate");
        boolean orderDateFlag = orderService.checkOrderDate(orderDate);

        return new Result(true, MessageConstant.ORDER_SUCCESS);
    }
}
