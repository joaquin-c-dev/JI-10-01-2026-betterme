package dev.joaquincoronado.betterme.user.controller.async.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SyncUserInfoQueue {

    public static final String QUEUE_NAME = "betterme.user.sync.queue";
    public static final String ROUTING_KEY = "betterme.user.sync.key";

    @Bean
    public Queue syncUserQueue(){
        return QueueBuilder.durable(QUEUE_NAME)
            .withArgument("x-queue-type", "quorum")
            .build();
    }

    @Bean
    public Binding binding(Queue syncUserQueue, DirectExchange exchange){
        return BindingBuilder.bind(syncUserQueue).to(exchange).with(ROUTING_KEY);
    }

}
