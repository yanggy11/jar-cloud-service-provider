package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.mapper.UserMapper;
import com.yanggy.cloud.param.UserParam;
import com.yanggy.cloud.service.IUserService;
import com.yanggy.cloud.utils.PasswordUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by yangguiyun on 2017/6/15.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
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
        user.setPassword(PasswordUtil.md5Encoder(user.getPassword(), null));
        return userMapper.insertUser(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public Page<List<User>> getUserList(UserParam userParam) {
        Page page = new Page();
        page.setPageSize(userParam.getPageSize());
        page.setPage(userParam.getPage());
        int count = userMapper.countUsers(userParam);
        page.setTotalRecord(count);
        page.setTotalPage(count % userParam.getPageSize() == 0 ? count / userParam.getPageSize() : count / userParam.getPageSize() + 1);
        userParam.setOffset((userParam.getPage() -1) * userParam.getPageSize());
        page.setData(userMapper.getUserList(userParam));

        return page;
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return new ResponseEntity<>(userMapper.deleteUser(userId));
    }

    @Override
    public ResponseEntity<?> editPassword(UserParam userParam) {
        if(!userParam.getPassword().equals(userParam.getConfirmPassword())) {
            return new ResponseEntity<>("密码不一致，请重新输入",null);
        }

        userParam.setPassword(PasswordUtil.md5Encoder(userParam.getPassword(),null));
        userMapper.editPassword(userParam);
        return new ResponseEntity<>(null);
    }

    @Override
    public ResponseEntity<?> deleteBatchUser(List<Long> userIds) {
        return new ResponseEntity<>(userMapper.deleteBatchUser(userIds));
    }
}
