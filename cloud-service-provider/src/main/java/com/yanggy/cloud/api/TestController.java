package com.yanggy.cloud.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by derrick.yang on 6/22/17.
 */

@RestController
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
