package com.yanggy.cloud.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author derrick.yang
 * @Date 8/23/18 09:25
 */

@Data
@RefreshScope
@ConfigurationProperties
@Component
public class RabbitMqProperties {

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private String port;
    @Value("${rabbitmq.username}")
    private String name;
    @Value("${rabbitmq.password}")
    private String password;
}
