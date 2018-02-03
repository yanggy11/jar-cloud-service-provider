package com.yanggy.cloud.service;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.es.Person;
import com.yanggy.cloud.entity.mongo.MongoTest;

/**
 * Created by derrick.yang on 1/19/18.
 */
public interface PersonService {
    ResponseEntity<?> save(Person person);

    ResponseEntity<?> save(MongoTest mongoTest);
}
