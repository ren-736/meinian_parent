package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    void add(OrderSetting orderSetting);
    int findCountByOrderDate(Date orderDate);
    void editNumberByOrderDate(OrderSetting orderSetting);
    List<OrderSetting>getOrderSettingByMonth(Map map);
    void editNumberByDate(OrderSetting orderSetting);
}
