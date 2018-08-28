package com.yanggy.cloud.common.config.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author derrick.yang
 * @Date 8/28/18 17:48
 */

@Component
public class SendMq {
    @Autowired
    private AmqpTemplate amqpTemplate;


}
