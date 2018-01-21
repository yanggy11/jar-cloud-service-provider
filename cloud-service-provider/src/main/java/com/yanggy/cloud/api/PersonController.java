package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.es.Person;
import com.yanggy.cloud.entity.mongo.MongoTest;
import com.yanggy.cloud.service.PersonService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private PersonService personService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @PostMapping(value = "save")
    public ResponseEntity<?> save(@RequestBody Person person) {
        String value = redisTemplate.boundValueOps("mongo").get();
        kafkaTemplate.send(this.topic,"111");
        return personService.save(person);
    }

    @KafkaListener(id = "test", topics = "test")
    public void listner(ConsumerRecord consumerRecord) {
        System.out.println(consumerRecord);
    }


    @PostMapping(value = "saveMongoTest")
    public ResponseEntity<?> saveMongoTest(@RequestBody MongoTest mongoTest) {
       redisTemplate.opsForValue().set("mongo", "monbgo");
        return personService.save(mongoTest);
    }
}
