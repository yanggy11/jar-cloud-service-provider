package com.yanggy.cloud.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by derrick.yang on 12/15/17.
 */

@Data
@Component
@ConfigurationProperties(prefix = MasterDataSourceConfig.PREFIX, ignoreUnknownFields = false)
public class SlaveDataSOurceConfig implements Serializable {
    public static final String PREFIX = "slave.jdbc";
    private String type;
    private String driver;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
}
