package com.yanggy.cloud.common.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author derrick.yang
 * @Date 8/28/18 17:48
 */

@Component
public class SendMq {
    private static final Logger logger = LoggerFactory.getLogger(SendMq.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMq(String exchange, String routingkey, Object message) {

        logger.info("发送mq,exchange={},routingkey={},msg={}",exchange, routingkey, message);
        amqpTemplate.convertAndSend(exchange, routingkey, message);
    }
}
