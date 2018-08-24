package com.yanggy.cloud.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author derrick.yang
 * @Date 8/24/18 14:52
 */


@Data
@Document(collection = "mongo_test")
public class MongoTest implements Serializable {

    @Id
    private String id;

    private String name;
}
