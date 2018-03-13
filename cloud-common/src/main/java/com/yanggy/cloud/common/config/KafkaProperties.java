package com.yanggy.cloud.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by derrick.yang on 1/23/18.
 */

@Configuration
@Data
@ConfigurationProperties(prefix = KafkaProperties.KAFKA_PREFIX)
public class KafkaProperties {
    public final static String KAFKA_PREFIX = "kafka";


    private String bootstrapServers;
    private String topic;
}
