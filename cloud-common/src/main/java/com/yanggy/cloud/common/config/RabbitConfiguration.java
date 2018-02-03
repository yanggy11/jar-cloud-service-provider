package com.yanggy.cloud.common.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by derrick.yang on 12/16/17.
 */

@Configuration
public class RabbitConfiguration {
    @Value("${spring.rabbitmq.host}")
    private String hostname;

    private final static String USER_EXCHANGE = "user_exchange";
    private final static String USER_QUEUE = "hello";
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(this.USER_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(this.USER_QUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
        rabbitTemplate.setMessageConverter(this.jsonMessageConverter());
        System.out.println(connectionFactory.getUsername());
        return rabbitTemplate;
    }
}
