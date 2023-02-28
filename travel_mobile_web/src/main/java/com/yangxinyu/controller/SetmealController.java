package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.Result;
import com.yangxinyu.entity.Setmeal;
import com.yangxinyu.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    /**
     * 获取所有套餐
     * @return
     */
    @RequestMapping("/getAll")
    public Result getAll(){
        List<Setmeal> setmeals = setmealService.getAll();
        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,setmeals);
    }

    /**
     * 获取套餐详情，包括组团和自由行
     * @param id
     * @return
     */
    @RequestMapping("/getSetmealById")
    public Result getSetmealById(Integer id){
        Setmeal setmeal = setmealService.getSetmealById(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    /**
     * 获取套餐简单信息，不包括组团和自由行
     * @param id
     * @return
     */
    @RequestMapping("/getSetmealByIdSimple")
    public Result getSetmealByIdSimple(Integer id){
        Setmeal setmeal = setmealService.getSetmealByIdSimple(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @RequestMapping("/sendMessageCode")
    public Result sendMessageCode(String phone){
        try {
            setmealService.sendMessageCode(phone);
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }

    }
}
