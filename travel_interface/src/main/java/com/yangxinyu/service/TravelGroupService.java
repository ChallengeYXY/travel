package com.yangxinyu.service;

import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.TravelGroup;

import java.util.List;

public interface TravelGroupService {
    public void addTravelGroup(Integer[] travelItemIds, TravelGroup travelGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    TravelGroup getTravelGroupById(Integer id);

    void updateTravelGroup(TravelGroup travelGroup);

    void deleteAllByTravelGroupId(Integer travelGroupId);

    void addTravelgroupAndTravelitem(Integer travelGroupId, Integer[] travelItemIds);

    List<TravelGroup> getAllTravelGroup();
}
