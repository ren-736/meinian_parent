package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

public interface SetmealService {
    public void add(Setmeal setmeal,Integer[] travelGroupIds);
    PageResult findPage(Integer size, String s, Integer current);
}
