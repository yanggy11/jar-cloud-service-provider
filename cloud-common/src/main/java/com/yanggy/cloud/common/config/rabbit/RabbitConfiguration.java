package com.yanggy.cloud.common.config.rabbit;

import com.yanggy.cloud.common.utils.Constants;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    public Queue helloQueue() {
        return new Queue(Constants.RabbitConstants.USER_QUEUE);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMqProperties.getHost());
        connectionFactory.setPort(Integer.parseInt(rabbitMqProperties.getPort()));
        connectionFactory.setUsername(rabbitMqProperties.getName());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setPublisherConfirms(true); //必须要设置

        return connectionFactory;
    }


    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(Constants.RabbitConstants.USER_EXCHANGE);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(helloQueue()).to(directExchange()).with(Constants.RabbitConstants.USER_QUEUE);
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
