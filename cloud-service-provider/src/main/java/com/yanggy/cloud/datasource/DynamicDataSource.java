package com.yanggy.cloud.datasource;

/**
 * Created by yangguiyun on 2017/9/28.
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 动态数据源路由，读写使用主库，只读使用从库
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 从数据源
     */
    private List<Object> slaveDataSources = new ArrayList<Object>();
    /**
     * 轮询计数
     */
    private AtomicInteger squence = new AtomicInteger(0);

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        return DynamicDataSourceHolder.get();
    }

    public void setSlaveDataSources(List<Object> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    /**
     * 轮询获取从库
     *
     * @return
     */
    public Object getSlaveKey() {
        if (squence.intValue() == Integer.MAX_VALUE) {
            synchronized (squence) {
                if (squence.intValue() == Integer.MAX_VALUE) {
                    squence = new AtomicInteger(0);
                }
            }
        }
        int idx = squence.getAndIncrement() % slaveDataSources.size();
        return slaveDataSources.get(idx);
    }
}
