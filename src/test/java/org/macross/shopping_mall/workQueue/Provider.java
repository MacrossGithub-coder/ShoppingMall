package org.macross.shopping_mall.workQueue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Provider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void Provider() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("workQueue", "work Queue");
        }
    }
}
