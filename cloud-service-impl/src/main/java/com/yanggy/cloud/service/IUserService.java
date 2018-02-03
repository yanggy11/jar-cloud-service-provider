package com.yanggy.cloud.service;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.param.UserParam;

import java.util.List;

/**
 * Created by yangguiyun on 2017/6/15.
 */
public interface IUserService {
    User login(User user);
    User getUserById(long id);
    int register(User user);
    int update(User user);
    Page<List<User>> getUserList(UserParam userParam);
    ResponseEntity<?> deleteUser(Long userId);
    ResponseEntity<?> editPassword(UserParam userParam);
}
