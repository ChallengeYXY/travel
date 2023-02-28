package com.yangxinyu.service;

import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.TravelItem;

import java.util.List;

public interface TravelItemService {
    public void add(TravelItem travelItem);

    public PageResult queryPage(QueryPageBean queryPageBean);

    void deleteTravelItem(Integer id);

    TravelItem queryTravelItemById(Integer id);

    void editTravelItem(TravelItem travelItem);

    List<TravelItem> findAll();
}
