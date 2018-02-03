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
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(Integer.parseInt(redisProperties.getMaxIdle()));
        jedisPoolConfig.setMaxWaitMillis(Long.parseLong(redisProperties.getMaxWait()));
        jedisPoolConfig.setMinIdle(Integer.parseInt(redisProperties.getMinIdl()));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(redisProperties.getMaxActive()));

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        jedisConnectionFactory.setPassword(redisProperties.getPassword());
        jedisConnectionFactory.setPort(Integer.parseInt(redisProperties.getPort()));
        jedisConnectionFactory.setTimeout(Integer.parseInt(redisProperties.getTimeout()));
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setDatabase(Integer.parseInt(redisProperties.getDatabase()));

        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate redisTemplate() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());

        return cacheManager;
    }
}
