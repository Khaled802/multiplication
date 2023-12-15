package com.example.multiplication.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {
    @Autowired
    AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange getAttemptExchange(@Value("${amqp.exchange.attempts}") String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Jackson2JsonMessageConverter producorJackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
}
