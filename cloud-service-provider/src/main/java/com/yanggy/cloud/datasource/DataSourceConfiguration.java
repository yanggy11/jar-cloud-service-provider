package com.yanggy.cloud.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangguiyun on 2017/9/28.
 */

@Configuration
public class DataSourceConfiguration {

    @Autowired
    private MasterDataSourceConfig masterDataSourceConfig;

    @Autowired
    private SlaveDataSOurceConfig slaveDataSOurceConfig;

    /**]
     * 主数据源
     * @return
     */
    @Bean(name = "masterDataSource", destroyMethod = "close", initMethod = "init")
    @Primary// 这个注解表示主数据源
    public DataSource masterDatasouce() {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUsername(masterDataSourceConfig.getUsername());
        druidDataSource.setPassword(masterDataSourceConfig.getPassword());
        druidDataSource.setDriverClassName(masterDataSourceConfig.getDriver());
        druidDataSource.setUrl(masterDataSourceConfig.getUrl());
        druidDataSource.setInitialSize(masterDataSourceConfig.getInitialSize());
        druidDataSource.setMaxActive(masterDataSourceConfig.getMaxActive());
        druidDataSource.setMaxWait(masterDataSourceConfig.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(masterDataSourceConfig.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(masterDataSourceConfig.getMinEvictableIdleTimeMillis());

        return druidDataSource;
    }

    /**
     * 从数据源
     * @return
     */
    @Bean(name = "slaveDataSource", destroyMethod = "close", initMethod = "init")
    public DataSource slaveDataSource() {
        DruidDataSource slaveDatasource = new DruidDataSource();

        slaveDatasource.setUsername(slaveDataSOurceConfig.getUsername());
        slaveDatasource.setPassword(slaveDataSOurceConfig.getPassword());
        slaveDatasource.setDriverClassName(slaveDataSOurceConfig.getDriver());
        slaveDatasource.setUrl(slaveDataSOurceConfig.getUrl());
        slaveDatasource.setInitialSize(slaveDataSOurceConfig.getInitialSize());
        slaveDatasource.setMaxActive(slaveDataSOurceConfig.getMaxActive());
        slaveDatasource.setTimeBetweenEvictionRunsMillis(masterDataSourceConfig.getTimeBetweenEvictionRunsMillis());
        slaveDatasource.setMaxWait(slaveDataSOurceConfig.getMaxWait());
        slaveDatasource.setMinEvictableIdleTimeMillis(masterDataSourceConfig.getMinEvictableIdleTimeMillis());


        return slaveDatasource;
    }

    /**
     * 构建动态数据源
     * @param masterDataSource
     * @param slaveDataSource
     * @return
     */
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave1", slaveDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);

        List<Object> slaveDataSources = new ArrayList<Object>();
        slaveDataSources.add("slave1");

        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setSlaveDataSources(slaveDataSources);

        return dynamicDataSource;

    }
}
