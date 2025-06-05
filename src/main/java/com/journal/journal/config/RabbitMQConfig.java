package com.journal.journal.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.routing_key}")
    private String key;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    @Bean
    public Binding builder(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(key);
    }

}
