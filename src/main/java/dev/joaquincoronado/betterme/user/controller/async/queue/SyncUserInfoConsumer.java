package dev.joaquincoronado.betterme.user.controller.async.queue;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class SyncUserInfoConsumer {

    @RabbitListener(queues = SyncUserInfoQueue.QUEUE_NAME)
    public void consumer(){
        log.info("QUEUE: SyncUserInfoConsumer START");
        int[] numbers = {1, 2, 3, 4, 5};
        for (int number : numbers) {
            try {
                Thread.sleep(1000);
                log.info("QUEUE: " + number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("QUEUE: SyncUserInfoConsumer END");
    }
}
