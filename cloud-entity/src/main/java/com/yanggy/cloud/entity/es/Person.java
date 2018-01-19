package com.yanggy.cloud.entity.es;

import com.yanggy.cloud.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by derrick.yang on 1/19/18.
 */

@Data
@Document(indexName = "person", type = "hello")
public class Person extends BaseEntity {
    private String personName;
    private String sex;
    private int age;
    private String cardId;
    private String phone;
    private String email;
}
