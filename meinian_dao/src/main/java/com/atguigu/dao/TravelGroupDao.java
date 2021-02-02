package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {
    List<TravelItem> getItems();
    void add(TravelGroup travelGroup);
    void setTravelGroupAndTravelItem(Map<String,Integer>map);
    Page<TravelGroup> getPage(String queryString);
    void delById(Integer id);
    TravelGroup getById(Integer id);
    List<Integer> getByGId(Integer id);
    void edit(TravelGroup travelGroup);
    List<TravelGroup>getAll();
}
