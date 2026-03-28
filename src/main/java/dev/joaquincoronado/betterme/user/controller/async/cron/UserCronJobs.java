package dev.joaquincoronado.betterme.user.controller.async.cron;

import dev.joaquincoronado.betterme.user.controller.async.queue.SyncUserInfoProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class UserCronJobs {

    private final SyncUserInfoProducer syncUserInfoProducer;

    //@Scheduled(cron = "0 0 4 * * *")
    @Scheduled(cron = "*/5 * * * * *")
    public void syncUserInformation() {
        log.info("CRON: SyncUserInfoProducer START");
        this.syncUserInfoProducer.startUserInfoSync(1L);
        log.info("CRON: SyncUserInfoProducer END");
    }
}
