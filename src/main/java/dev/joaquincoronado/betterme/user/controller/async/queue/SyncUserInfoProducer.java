package dev.joaquincoronado.betterme.user.controller.async.queue;

import dev.joaquincoronado.betterme.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncUserInfoProducer {
    private final RabbitTemplate rabbitTemplate;

    public void startUserInfoSync(Long userId){
        this.rabbitTemplate.convertAndSend(
            RabbitMQConfig.BETTERME_EXCHANGE,
            SyncUserInfoQueue.ROUTING_KEY,
            userId
        );
    }

}
