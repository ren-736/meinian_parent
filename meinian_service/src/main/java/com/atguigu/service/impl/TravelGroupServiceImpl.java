package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {
    @Autowired
    TravelGroupDao travelGroupDao;

    @Override
    public List<TravelItem> getItems() {
        List<TravelItem> items = travelGroupDao.getItems();
        return items;
    }

    @Override
    public void add(TravelGroup travelGroup, Integer[] ids) {
        travelGroupDao.add(travelGroup);
        setTravelGroupAndTravelItem(travelGroup.getId(),ids);
    }

    @Override
    public PageResult getPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelGroup> page = travelGroupDao.getPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public TravelGroup getById(Integer id) {
        return travelGroupDao.getById(id);
    }

    @Override
    public void edit(Integer[] items, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        travelGroupDao.delById(travelGroup.getId());
        setTravelGroupAndTravelItem(travelGroup.getId(),items);
    }


    @Override
    public List<Integer> getByGId(Integer id) {
        return travelGroupDao.getByGId(id);
    }

    @Override
    public List<TravelGroup> getAll() {
        return travelGroupDao.getAll();

    }


    private void setTravelGroupAndTravelItem(Integer id,Integer[] ids){
        if (ids!=null && ids.length>0){
            for (Integer i : ids) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("travelGroup",id);
                map.put("travelItem",i);
                travelGroupDao.setTravelGroupAndTravelItem(map);
            }
        }
    }
}
