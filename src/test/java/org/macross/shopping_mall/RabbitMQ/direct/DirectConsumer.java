package org.macross.shopping_mall.RabbitMQ.direct;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "direct",durable = "false",autoDelete = "false",exclusive = "false"))
public class DirectConsumer {

    @RabbitHandler
    public void Consumer(String message){
        System.out.println("message:"+message);
    }
}
