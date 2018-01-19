package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.es.Person;
import com.yanggy.cloud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by derrick.yang on 1/19/18.
 */

@RestController
@RequestMapping("/person/**")
public class PersonController {
    @Autowired
    private PersonService personService;
    @PostMapping(value = "save")
    public ResponseEntity<?> save(@RequestBody Person person) {
        return personService.save(person);
    }
}
