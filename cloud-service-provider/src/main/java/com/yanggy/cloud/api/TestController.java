package com.yanggy.cloud.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by derrick.yang on 6/22/17.
 */

@RefreshScope
@RestController
public class TestController {

    @Value("${jdbc.username}")
    private String value;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return value;
    }
}
