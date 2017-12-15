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
#配置mysql主从备份

1.docker搭建主从

从docker hub上下载最新的MySQL镜像，遵循如下步骤，即可利用docker构建MySQL主从环境。

1.运行主库容器
```
docker run --name mysql -p 33061:3306 -e MYSQL_ROOT_PASSWORD=root -v /Users/derrick.yang/mysql/data/:/var/lib/mysql/ -v /Users/derrick.yang/mysql/etc/:/etc/mysql/ 5fac85ee2c68
```

2.运行从库容器
```
docker run --name mysql -p 33062:3306 -e MYSQL_ROOT_PASSWORD=root -v /Users/derrick.yang/mysql/data/:/var/lib/mysql/ -v /Users/derrick.yang/mysql/etc/:/etc/mysql/ 5fac85ee2c68
```
3.修改配置文件

主库配置文件
```
server-id=1
log-bin=master-bin
log-bin-index=master-bin.index
```
重启主库,查看主库状态
```
show master status;
```
从库配置文件
```
server-id=10 #唯一值，不要与主库相同
log-bin=master-bin
log-bin-index=master-bin.index
```
重新启动从库，配置从库访问主库的信息：
```
change master to master_host='104.224.163.143',master_user='root',master_password='root',master_log_file='master-bin.000006',master_log_pos=107;
```
启动slave,查看状态
```
start slave;

show slave status\G;
```
