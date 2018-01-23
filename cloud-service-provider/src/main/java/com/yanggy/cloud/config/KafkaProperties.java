package com.yanggy.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by derrick.yang on 1/23/18.
 */

@Component
@ConfigurationProperties(prefix = KafkaProperties.KAFKA_PREFIX)
@Data
@RefreshScope
public class KafkaProperties {
    public final static String KAFKA_PREFIX = "kafka";

    private String bootstrapServers;
    private String topic;
}
