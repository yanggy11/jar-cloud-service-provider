package com.yanggy.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by yangguiyun on 2017/6/12.
 */

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRegister {
    public static void main(String[] args) {
       new SpringApplicationBuilder(ServiceRegister.class).web(true).run(args);
    }
}
