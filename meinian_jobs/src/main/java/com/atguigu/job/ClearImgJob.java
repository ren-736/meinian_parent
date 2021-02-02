package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void clearImg(){
        Set<String> set =
                jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println("["+next+"]已被删除");
            QiniuUtils.deleteFileFromQiniu(next);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,next);
        }
    }
}
