package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.es.Person;
import com.yanggy.cloud.repository.elasticsearch.PersonRepository;
import com.yanggy.cloud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by derrick.yang on 1/19/18.
 */

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public ResponseEntity<?> save(Person person) {

        return new ResponseEntity<>(personRepository.save(person));
    }
}
