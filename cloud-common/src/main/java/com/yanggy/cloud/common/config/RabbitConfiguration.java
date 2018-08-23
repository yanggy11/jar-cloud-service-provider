package com.yanggy.cloud.common.config;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
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


@Data
@Configuration
public class RabbitConfiguration {
    @Autowired
    private RabbitMqProperties rabbitMqProperties;

    private final static String USER_EXCHANGE = "user_exchange";
    private final static String USER_QUEUE = "hello";
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMqProperties.getHost());
        connectionFactory.setPort(Integer.parseInt(rabbitMqProperties.getPort()));
        connectionFactory.setUsername(rabbitMqProperties.getName());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); //必须要设置

        return connectionFactory;
    }


        @Bean
    DirectExchange directExchange() {
        return new DirectExchange(this.USER_EXCHANGE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(helloQueue()).to(directExchange()).with(this.USER_QUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(this.jsonMessageConverter());

        return rabbitTemplate;
    }
}
