package com.yangxinyu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.Result;
import com.yangxinyu.entity.TravelItem;
import com.yangxinyu.service.TravelItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    /**
     * 添加自由行
     * @param travelItem
     * @return
     */
    @RequestMapping("/add")
    public Result addTravelItem(@RequestBody TravelItem travelItem){
        try {
            System.out.println(travelItem);
            travelItemService.add(travelItem);
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS,travelItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        }
    }


    /**
     * 分页查询自由行
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelItemService.queryPage(queryPageBean);
        return pageResult;
    }

    /**
     * 删除自由行
     * @param id
     */
    @RequestMapping("/delete")
    public Result deleteTravelItem(@RequestParam("id") Integer id){
        try {
            travelItemService.deleteTravelItem(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }

    }

    /**
     * 通过id查询自由行
     * @param id
     * @return
     */
    @RequestMapping("/query")
    public Result queryTravelItemById(@RequestParam("id") Integer id){
        try {
            TravelItem travelItem = travelItemService.queryTravelItemById(id);
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_FAIL,travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    /**
     * 编辑自由行
     * @param travelItem
     * @return
     */
    @RequestMapping("/edit")
    public Result editTravelItem(@RequestBody TravelItem travelItem){
        try {
            travelItemService.editTravelItem(travelItem);
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelItem> travelItems = travelItemService.findAll();
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItems);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }
}
