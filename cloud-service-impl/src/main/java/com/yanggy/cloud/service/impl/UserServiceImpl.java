package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.mapper.UserMapper;
import com.yanggy.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangguiyun on 2017/6/15.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.findByName(user.getName());
    }

    @Override
    public User getUserById(long id) {
        return userMapper.selectById(id);
    }

    @Override
    public int register(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
