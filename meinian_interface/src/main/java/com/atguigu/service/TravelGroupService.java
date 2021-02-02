package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;

import java.util.List;

public interface TravelGroupService {
    List<TravelItem> getItems();
    void add(TravelGroup travelGroup,Integer[] ids);
    PageResult getPage(Integer currentPage,Integer pageSize,String queryString);
    TravelGroup getById(Integer id);
    void edit(Integer[] Items,TravelGroup travelGroup);
    List<Integer> getByGId(Integer id);
//    void delById(Integer id);
    List<TravelGroup>getAll();
}
