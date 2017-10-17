package com.yanggy.cloud.service;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.param.UserParam;

import java.util.Map;

/**
 * Created by yangguiyun on 2017/6/15.
 */
public interface IUserService {
    User login(User user);
    User getUserById(long id);
    int register(User user);
    Page<?> getUserList(UserParam userParam);
}
