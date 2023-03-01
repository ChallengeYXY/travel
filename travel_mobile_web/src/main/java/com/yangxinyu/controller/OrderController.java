package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.Member;
import com.yangxinyu.entity.Order;
import com.yangxinyu.entity.Result;
import com.yangxinyu.service.MemberService;
import com.yangxinyu.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Reference
    private MemberService memberService;
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
        if (!orderDateFlag){
            //当前日期不可以进行预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        try {
            //用户信息处理
            String name = map.get("name");
            String sex = map.get("sex");
            String idCard = map.get("idCard");
            Member member = new Member();
            member.setName(name);
            member.setSex(sex);
            member.setIdCard(idCard);
            member.setRegTime(new Date());
            memberService.checkMember(member);
            //处理预约信息
            String setmealId = map.get("setmealId");
            Order order = new Order();
            order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse(orderDate));
            order.setOrderStatus(Order.ORDERSTATUS_NO);
            order.setSetmealId(Integer.parseInt(setmealId));
            orderService.addOrder(member,order);
            return new Result(true, MessageConstant.ORDER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.ORDER_FAIL);
        }


    }
}
