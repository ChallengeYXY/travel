package com.yangxinyu.dao;

import com.github.pagehelper.Page;
import com.yangxinyu.entity.Setmeal;

import java.util.List;

public interface SetmealDao {
    /**
     * 向套餐游表添加数据
     * @param setmeal
     */
    public void addSetmeal(Setmeal setmeal);


    /**
     * 向套餐游表与跟团游表的中间表插入数据
     * @param setmealId
     * @param travelgroupId
     */
    public void addSetmealAndTravelGroup(Integer setmealId, Integer travelgroupId);

    /**
     * 分页查询套餐游
     * @param queryString
     * @return
     */
    public Page findPage(String queryString);

    /**
     * 获取套餐列表
     * @return
     */
    List<Setmeal> getAll();


    /**
     * 根据id获取套餐简单信息
     * @return
     */
    Setmeal getSetmealByIdSimple(Integer id);

    /**
     * 根据套餐id查询组团游id
     * @param id
     * @return
     */
    List<Integer> getTravelGroupIds(Integer id);

    /**
     * 根据id更新套餐信息
     * @param setmeal
     */
    void updateSetmeal(Setmeal setmeal);

    /**
     * 删除中间表
     * @param setmealId
     */
    void deleteSetmealAndTravelGroup(Integer setmealId);

    /**
     * 删除套餐与跟团游中间表
     * @param id
     */
    void deleteSetmealAndTravelGroupById(Integer id);

    /**
     * 删除套餐
     * @param id
     */
    void deleteSetmealById(Integer id);
}
