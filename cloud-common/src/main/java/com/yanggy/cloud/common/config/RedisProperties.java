package com.yanggy.cloud.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: yangguiyun
 * @Date: 2018/1/22 14:10
 * @Description:
 */

@Data
@RefreshScope
@ConfigurationProperties
@Component
public class RedisProperties {
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
}
