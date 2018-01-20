package com.yanggy.cloud.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: yangguiyun
 * @Date: 2018/1/20 15:38
 * @Description:
 */

@Data
@Document(collection = "mongo_test")
public class MongoTest {
    @Id
    private String id;
    private String name;
    private String sex;
    private int age;
}
