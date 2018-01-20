package com.yanggy.cloud.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: yangguiyun
 * @Date: 2018/1/20 16:51
 * @Description:
 */

@Configuration
@Data
public class RedisConfiguration {

    @Value("${redis.database}")
    private String database;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.pool.max-active}")
    private String maxActive;
    @Value("${redis.pool.max-wait}")
    private String maxWait;
    @Value("${redis.pool.max-idle}")
    private String maxIdle;
    @Value("${redis.min-idle}")
    private String minIdl;
    @Value("${redis.timeout}")
    private String timeout;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(Integer.parseInt(this.maxIdle));
        jedisPoolConfig.setMaxWaitMillis(Long.parseLong(this.maxWait));
        jedisPoolConfig.setMinIdle(Integer.parseInt(this.minIdl));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(this.maxActive));

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        jedisConnectionFactory.setPassword(this.password);
        jedisConnectionFactory.setPort(Integer.parseInt(this.port));
        jedisConnectionFactory.setTimeout(Integer.parseInt(this.timeout));
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setHostName(this.host);
        jedisConnectionFactory.setDatabase(Integer.parseInt(this.database));

        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        return redisTemplate;
    }
}
