package org.macross.shopping_mall.routing;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Provider {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Test
    public void Provider(){
        rabbitTemplate.convertAndSend("logs_direct","error","a error message");
    }

}
