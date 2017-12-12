# jar-cloud-service-provider
#搭建服务提供者 

引入jar包
```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```

#启动类中加入注解@EnableDiscoveryClient开启服务Eureka的服务发现功能。

#指定服务注册中心

```
server:
  port: 2015

spring:
  application:
    name: cloud-service-product

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8080/eureka/
```
