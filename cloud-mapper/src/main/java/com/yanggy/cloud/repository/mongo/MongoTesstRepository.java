package com.yanggy.cloud.repository.mongo;

import com.yanggy.cloud.entity.mongo.MongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author derrick.yang
 * @Date 8/24/18 14:54
 */
public interface MongoTesstRepository extends MongoRepository<MongoTest, String> {
}
