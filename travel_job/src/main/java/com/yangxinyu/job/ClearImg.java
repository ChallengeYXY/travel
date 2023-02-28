package com.yangxinyu.job;

import com.yangxinyu.qiniu.QiniuTools;
import com.yangxinyu.qiniu.QiniuUtils;
import com.yangxinyu.qiniu.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImg {

    @Autowired
    JedisPool jedisPool;

    public void clearSetmealImg(){
        System.out.println("执行成功！");
        Jedis jedisPoolResource = jedisPool.getResource();
        Set<String> rubbishImgs = jedisPoolResource.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        System.out.println(rubbishImgs);
        for (String rubbishImg : rubbishImgs) {
            QiniuUtils.deleteFileFromQiniu(rubbishImg);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,rubbishImg);
        }

    }
}
