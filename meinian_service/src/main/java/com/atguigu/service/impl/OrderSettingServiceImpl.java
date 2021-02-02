package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Override
    public void add(List<OrderSetting> orderSettings) {
        for (OrderSetting setting : orderSettings) {
            int count = orderSettingDao.findCountByOrderDate(setting.getOrderDate());
            if (count>0){
                orderSettingDao.editNumberByOrderDate(setting);
            }else {
                orderSettingDao.add(setting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String dateBegin = date + "-1";
        String dateEnd = date + "-31";
        Map<String,Object> map = new HashMap<>();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        map.put("NowData",date);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());
            orderSettingMap.put("number",orderSetting.getNumber());
            orderSettingMap.put("reservations",orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        int count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            orderSettingDao.editNumberByDate(orderSetting);
        }else {
            orderSettingDao.add(orderSetting);
        }
    }
}
