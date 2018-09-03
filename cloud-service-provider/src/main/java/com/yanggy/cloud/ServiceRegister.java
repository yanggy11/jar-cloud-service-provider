package com.yanggy.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangguiyun on 2017/6/12.
 */

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRegister {

    @Bean
    public ExecutorService getThreadPool(){
        return Executors.newFixedThreadPool(100);
    }
    public static void main(String[] args) {
       new SpringApplicationBuilder(ServiceRegister.class).web(true).run(args);
    }
}
