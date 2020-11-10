package org.macross.shopping_mall.fanout;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Provider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void Provider(){
        rabbitTemplate.convertAndSend("logs","","fanout 广播模式！");
    }
}
