package org.macross.shopping_mall.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoutingConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
                                            key = {"error"},
                                            exchange = @Exchange(name = "logs_direct")))
    public void Consumer1(String message){
        System.out.println("消费者1——message："+message);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            key = {"error","info","warning"},
            exchange = @Exchange(name = "logs_direct")))
    public void Consumer2(String message){
        System.out.println("消费者2——message："+message);
    }

}
