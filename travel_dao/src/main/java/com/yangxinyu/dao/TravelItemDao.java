package com.yangxinyu.dao;

import com.github.pagehelper.Page;
import com.yangxinyu.entity.TravelItem;

import java.util.List;

public interface TravelItemDao {
    //添加自由行
    public void add(TravelItem travelItem);

    //分页查询
    public Page queryPage(String queryString);

    //通过id删除自由行
    public void deleteTravelItem(Integer id);

    //通过id查询自由行
    TravelItem queryTravelItemById(Integer id);

    //修改自由行
    void editTravelItem(TravelItem travelItem);

    //查询全部自由行
    List<TravelItem> findAll();
}
