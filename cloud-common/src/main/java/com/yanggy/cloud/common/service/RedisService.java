package com.yanggy.cloud.common.service;

/**
 * Created by derrick.yang on 2/10/18.
 */
public interface RedisService {
    String get(String key);

    void set(String key, String value);

    void set(String key, String value, int expire);

    int incr(String key, int moutn);
}
