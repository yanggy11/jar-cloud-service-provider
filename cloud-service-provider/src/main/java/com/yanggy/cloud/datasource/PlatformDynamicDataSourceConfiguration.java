package com.yanggy.cloud.datasource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by yangguiyun on 2017/9/28.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(PlatformDynamicDataSourceConfiguration.MAPPER_PACKAGE_PATH)
public class PlatformDynamicDataSourceConfiguration {
    private final static String MAPPER_LOCATIONS = "sql-mapper/*.xml";
    private final static String ENTITY_PACKAGE_PATH = "com.yanggy.cloud.entity";
    protected final static String MAPPER_PACKAGE_PATH = "com.yanggy.cloud.mapper";

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws SQLException, IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("sqlType", "mysql");
        sqlSessionFactoryBean.setConfigurationProperties(properties);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS));
        sqlSessionFactoryBean.setTypeAliasesPackage(ENTITY_PACKAGE_PATH);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DynamicDataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
        DynamicDataSourceTransactionManager dynamicDataSourceTransactionManager = new DynamicDataSourceTransactionManager();
        dynamicDataSourceTransactionManager.setDataSource(dataSource);
        return dynamicDataSourceTransactionManager;
    }

}
