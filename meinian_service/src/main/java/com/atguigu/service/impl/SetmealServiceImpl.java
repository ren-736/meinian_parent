package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Override
    public void add(Setmeal setmeal, Integer[] travelGroupIds) {
        setmealDao.add(setmeal);
        if (travelGroupIds!=null&&travelGroupIds.length>0){
            setSetmealAndTravelGroup(setmeal.getId(),travelGroupIds);
        }
    }

    @Override
    public PageResult findPage(Integer size, String s, Integer current) {
        PageHelper.startPage(current,size);
        Page<Setmeal> page = setmealDao.findPage(s);
        return new PageResult(page.getTotal(),page.getResult());
    }

    private void setSetmealAndTravelGroup(Integer id,Integer[] integers){
        for (Integer checkgroupId : integers) {
            Map<String,Integer>map = new HashMap<>();
            map.put("travelgroup_id",checkgroupId);
            map.put("setmeal_id",id);
            setmealDao.setSetmealAndTravelGroup(map);
        }
    }
}
