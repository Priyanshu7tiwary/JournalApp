package com.journal.journal.producer;

import com.journal.journal.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message,String key,String exchange){
        rabbitTemplate.convertAndSend(exchange,key,message);

    }
}

