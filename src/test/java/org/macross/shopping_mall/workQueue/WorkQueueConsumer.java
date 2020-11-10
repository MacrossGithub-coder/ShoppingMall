package org.macross.shopping_mall.workQueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkQueueConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "workQueue"))
    public void Consumer1(String message){
        System.out.println("消费者1：message = "+message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "workQueue"))
    public void Consumer2(String message){
        System.out.println("消费者2：message = "+message);
    }
}
