package com.yangxinyu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yangxinyu.constant.MessageConstant;
import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.Result;
import com.yangxinyu.entity.TravelGroup;
import com.yangxinyu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {
    @Reference
    private TravelGroupService travelGroupService;

    /**
     * 添加跟团游
     * @param travelItemIds
     * @param travelGroup
     * @return
     */
    @RequestMapping("/add")
    public Result addTravelGroup(@RequestParam(value = "travelItemIds") Integer[] travelItemIds,
                                 @RequestBody TravelGroup travelGroup){

        try {
            travelGroupService.addTravelGroup(travelItemIds,travelGroup);

            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }


    /**
     * 分页查询跟团游
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = travelGroupService.findPage(queryPageBean);

            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,pageResult);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }

    }

    /**
     * 通过id查询跟团游
     * @param id
     * @return
     */
    @RequestMapping("/getTravelGroupById")
    public Result getTravelGroupById(Integer id){
        try {
            TravelGroup travelGroup = travelGroupService.getTravelGroupById(id);
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            System.out.println(travelGroupService.getTravelGroupById(id));
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    /**
     * 修改跟团游
     * @param travelGroup
     */
    @RequestMapping("/updateTravelGroup")
    public Result updateTravelGroup(@RequestParam(value = "travelItemIds") Integer[] travelItemIds,
                                  @RequestBody TravelGroup travelGroup){
        try {
            //修改跟团游第一窗口
            travelGroupService.updateTravelGroup(travelGroup);
            //删除对应对应travelGroupID的所有内容
            travelGroupService.deleteAllByTravelGroupId(travelGroup.getId());
            //向中间表重新添加数据
            travelGroupService.addTravelgroupAndTravelitem(travelGroup.getId(),travelItemIds);
            return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }

    @RequestMapping("/getAllTravelGroup")
    public Result getAllTravelGroup(){
        try {
            List<TravelGroup> travelGroups = travelGroupService.getAllTravelGroup();
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroups);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }
}
