package org.macross.shopping_mall.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "topics", type = "topic"),
                                            key = {"user.*"}))
    public void Consumer1(String message) {
        System.out.println("消费者1——message：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "topics", type = "topic"),
                                            key = {"commodity.*"}))    public void Consumer2(String message) {
        System.out.println("消费者2——message：" + message);
    }


}
