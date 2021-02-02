package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = TravelItemService.class) //发布服务,注册到zk中心
@Transactional //声明式事务，所有方法都增加事务
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    TravelItemDao travelItemDao;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页插件
        //开启分页功能
        //limit (currentPage-1)*pageSize,pageSize
        PageHelper.startPage(currentPage,pageSize); //  limit ?,?  第一个问号表示开始索引，第二个问号表示查询的条数
        Page page = travelItemDao.findPage(queryString); //返回当前页数据
        return new PageResult(page.getTotal(),page.getResult()); // 1.总记录数，2.分页数据集合
    }

    @Override
    public void del(Integer id) {
        try {
            long count = travelItemDao.findCountByTravelItemItemId(id);
            if (count > 0){
                throw new RuntimeException("不允许删除!");
            }
            travelItemDao.del(id);
        }catch (Exception e){

        }
    }

    @Override
    public TravelItem getById(Integer id) {
        return travelItemDao.getById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    // ctrl + i
    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }
}
