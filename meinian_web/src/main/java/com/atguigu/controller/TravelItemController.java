package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController  //组合注解 @Controller + @ResponseBody
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference // 远程调用服务
    TravelItemService travelItemService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelItemService.findPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),queryPageBean.getQueryString());
        return pageResult;
    }


    //表单项参数名称必须和实体对象的属性名称一致，提供对应set方法，框架创建对象并封装数据。
    @RequestMapping("/add")
    public Result add(@RequestBody TravelItem travelItem){//@RequestBody从请求体获取数据
        try {
            travelItemService.add(travelItem); // ctrl + Alt + t
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }
    @RequestMapping("/del")
    public Result del(Integer id){
        try {
            travelItemService.del(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        }catch (Exception e){
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    @RequestMapping("/getById")
    public Result getById(Integer id){
        TravelItem item = travelItemService.getById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,item);
    }


}
