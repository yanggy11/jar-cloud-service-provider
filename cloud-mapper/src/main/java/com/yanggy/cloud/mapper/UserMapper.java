package com.yanggy.cloud.mapper;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.param.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangguiyun on 2017/6/1.
 */

@Mapper
public interface UserMapper {
    int insertUser(User user);
    User selectById(@Param("id") long id);
    User findByName(@Param("username") String name);
    List<User> getUserList(UserParam userParam);
    int countUsers(UserParam userParam);

    int update(User user);

    int deleteUser(Long userId);

    int editPassword(UserParam userParam);

    int deleteBatchUser(List<Long> list);
}
