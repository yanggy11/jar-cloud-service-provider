package com.yanggy.cloud.api;

import com.yanggy.cloud.common.config.DataSourceProperties;
import com.yanggy.cloud.common.config.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by derrick.yang on 6/22/17.
 */

@RestController
public class TestController {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private KafkaProperties kafkaProperties;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return dataSourceProperties.getUsername();
    }
}
