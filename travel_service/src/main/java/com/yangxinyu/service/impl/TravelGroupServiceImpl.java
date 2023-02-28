package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yangxinyu.dao.TravelGroupDao;
import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.TravelGroup;
import com.yangxinyu.service.TravelGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupDao travelGroupDao;


    @Override
    public void addTravelGroup(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.addTravelGroup(travelGroup);
        Integer travelGroupId = travelGroup.getId();
        for (Integer travelItemId : travelItemIds) {
            travelGroupDao.addTravelgroupAndTravelitem(travelGroupId,travelItemId);
        }

    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //开启分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        //分页查询
        Page page = travelGroupDao.findPage(queryPageBean.getQueryString());

        //分页结果
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public TravelGroup getTravelGroupById(Integer id) {
        return travelGroupDao.getTravelGroupById(id);
    }

    /**
     * 更新跟团游信息
     * @param travelGroup
     */
    @Override
    public void updateTravelGroup(TravelGroup travelGroup) {
        travelGroupDao.updateTravelGroup(travelGroup);
    }

    /**
     * 根据跟团游ID删除中间表信息
     * @param travelGroupId
     */
    @Override
    public void deleteAllByTravelGroupId(Integer travelGroupId) {
        travelGroupDao.deleteAllByTravelGroupId(travelGroupId);
    }

    @Override
    public void addTravelgroupAndTravelitem(Integer travelGroupId, Integer[] travelItemIds) {
        for (Integer travelItemId : travelItemIds) {
            travelGroupDao.addTravelgroupAndTravelitem(travelGroupId,travelItemId);
        }
    }

    /**
     * 查询全部跟团游
     * @return List<TravelGroup>
     */
    @Override
    public List<TravelGroup> getAllTravelGroup() {
        List<TravelGroup> travelGroups = travelGroupDao.getAllTravelGroup();
        return travelGroups;
    }
}
