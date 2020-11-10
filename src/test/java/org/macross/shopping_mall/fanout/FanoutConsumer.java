package org.macross.shopping_mall.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
                                             exchange = @Exchange(name = "logs",type = "fanout")))
    public void consumer1(String message){
        System.out.println("消费者1：message："+message);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "logs",type = "fanout")))
    public void consumer2(String message){
        System.out.println("消费者2：message："+message);
    }
}
