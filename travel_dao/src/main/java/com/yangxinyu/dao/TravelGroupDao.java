package com.yangxinyu.dao;

import com.github.pagehelper.Page;
import com.yangxinyu.entity.TravelGroup;

import java.util.List;

public interface TravelGroupDao {
    //添加跟团游
    public void addTravelGroup(TravelGroup travelGroup);

    //添加跟团游与自由行中间表
    public void addTravelgroupAndTravelitem(Integer travelGroupId, Integer travelItemId);


    //分页查询跟团游
    public Page findPage(String queryString);

    TravelGroup getTravelGroupById(Integer id);

    void updateTravelGroup(TravelGroup travelGroup);

    void deleteAllByTravelGroupId(Integer travelGroupId);

    List<TravelGroup> getAllTravelGroup();
}
