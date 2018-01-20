package com.yanggy.cloud.repository.mongo;

import com.yanggy.cloud.entity.mongo.MongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: yangguiyun
 * @Date: 2018/1/20 15:42
 * @Description:
 */

@Repository
public interface MongoTestRepository extends MongoRepository<MongoTest, String>{
}
