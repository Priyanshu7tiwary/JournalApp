package com.journal.journal.Rabbit;

import com.journal.journal.producer.RabbitMqProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RabbitTest {

    @Autowired
    private RabbitMqProducer rabbitP;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void RabbitTest2(){
        rabbitP.sendMessage("test_rabbit","test2_route","journal");
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            String message = (String) rabbitTemplate.receiveAndConvert("test2");
            assertEquals("test_rabbit", message);
    });
}}
