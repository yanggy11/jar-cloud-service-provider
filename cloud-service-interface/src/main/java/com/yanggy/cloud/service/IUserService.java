package com.yanggy.cloud.service;

import com.yanggy.cloud.entity.User;

/**
 * Created by yangguiyun on 2017/6/15.
 */
public interface IUserService {
    User login(User user);
    User getUserById(long id);
    int register(User user);
}
