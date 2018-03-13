package com.yanggy.cloud.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: yangguiyun
 * @Date: 2018/1/20 16:51
 * @Description:
 */

@Configuration
@Data
public class RedisConfiguration {

    @Autowired
    private RedisProperties redisProperties;


    @Bean
    JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(Integer.parseInt(redisProperties.getMaxIdle()));
        jedisPoolConfig.setMaxWaitMillis(Long.parseLong(redisProperties.getMaxWait()));
        jedisPoolConfig.setMinIdle(Integer.parseInt(redisProperties.getMinIdl()));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(redisProperties.getMaxActive()));
        jedisPoolConfig.setTestOnBorrow(true);


        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), Integer.parseInt(redisProperties.getPort()),
                Integer.parseInt(redisProperties.getTimeout()), redisProperties.getPassword(), Integer.parseInt(redisProperties.getDatabase()));

    }
}
