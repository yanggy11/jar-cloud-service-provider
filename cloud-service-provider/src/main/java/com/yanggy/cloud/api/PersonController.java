package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.es.Person;
import com.yanggy.cloud.entity.mongo.MongoTest;
import com.yanggy.cloud.service.PersonService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private PersonService personService;
    @PostMapping(value = "save")
    public ResponseEntity<?> save(@RequestBody Person person) {
        kafkaTemplate.send("test","test","111");
        return personService.save(person);
    }

    @KafkaListener(id = "test", topics = "test")
    public void listner(ConsumerRecord consumerRecord) {
        System.out.println(consumerRecord);
    }


    @PostMapping(value = "saveMongoTest")
    public ResponseEntity<?> saveMongoTest(@RequestBody MongoTest mongoTest) {
        return personService.save(mongoTest);
    }
}
