package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {
    @Reference
    TravelGroupService travelGroupService;

    @RequestMapping("/getItems")
    public Result getItems(){
        List<TravelItem> items = travelGroupService.getItems();
        return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,items);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup,Integer[] travelItemIds){
        travelGroupService.add(travelGroup,travelItemIds);
        return new Result(true,MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }
    @RequestMapping("/getPage")
    public PageResult getPage(@RequestBody QueryPageBean queryPageBean){
        return travelGroupService.getPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
    }
    @RequestMapping("/getItemIdByGId")
    public List<Integer> getItemIdByGId(Integer id){
        return travelGroupService.getByGId(id);
    }
    @RequestMapping("/getById")
    public Result getById(Integer id){
        TravelGroup travelGroup = travelGroupService.getById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] ids,@RequestBody TravelGroup travelGroup){
        travelGroupService.edit(ids,travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> all = travelGroupService.getAll();
        if (all!=null&&all.size()>0)
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,all);
        return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
    }
}
