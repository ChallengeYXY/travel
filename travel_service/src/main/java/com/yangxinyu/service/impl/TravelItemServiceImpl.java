package com.yangxinyu.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yangxinyu.dao.TravelItemDao;
import com.yangxinyu.entity.PageResult;
import com.yangxinyu.entity.QueryPageBean;
import com.yangxinyu.entity.TravelItem;
import com.yangxinyu.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@Service
public class TravelItemServiceImpl implements TravelItemService {
    @Autowired
    private TravelItemDao travelItemDao;

    /**
     * 添加自由行
     * @param travelItem
     */
    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //开启分页功能
        /*
            需要传入当前页与每页条数
            在当前签方法开启分页之后执行的SQL语句，默认结尾添加一个limt(?,?)
            左闭右开
            第一个问号底层：（currentPage-1）*pageSize
         */
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page page = travelItemDao.queryPage(queryPageBean.getQueryString());

        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 通过id删除自由行
     * @param id
     */
    @Override
    public void deleteTravelItem(Integer id) {
        travelItemDao.deleteTravelItem(id);
    }

    /**
     * 通过id查找自由行
     * @param id
     * @return
     */
    @Override
    public TravelItem queryTravelItemById(Integer id) {
        TravelItem travelItem = travelItemDao.queryTravelItemById(id);
        return travelItem;
    }

    /**
     * 修改自由行
     * @param travelItem
     */
    @Override
    public void editTravelItem(TravelItem travelItem) {
        travelItemDao.editTravelItem(travelItem);
    }

    /**
     * 查询全部自由行
     * @return
     */
    @Override
    public List<TravelItem> findAll() {
        List<TravelItem> travelItems = travelItemDao.findAll();
        return travelItems;
    }
}
