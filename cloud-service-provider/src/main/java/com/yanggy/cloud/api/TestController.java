package com.yanggy.cloud.api;

import com.yanggy.cloud.common.config.DataSourceProperties;
import com.yanggy.cloud.common.config.KafkaProperties;
import com.yanggy.cloud.common.lock.RedisDistributeLock;
import com.yanggy.cloud.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * Created by derrick.yang on 6/22/17.
 */

@RestController
public class TestController {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private RedisDistributeLock redisDistributeLock;

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private RedisService redisService;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String requestId = UUID.randomUUID().toString();
            boolean locked = redisDistributeLock.tryGetLock(jedis, "lock", requestId, 100000000);
            if (locked) {
                System.out.println("获取到分布式事物锁");
            } else {
                System.out.println("锁被占用");
            }

//            Thread.sleep(5000);

            redisDistributeLock.unLock(jedis, "lock", requestId);

            System.out.println("释放锁");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != jedis) {
                jedis.close();
            }
        }

        return dataSourceProperties.getUsername();
    }
}
