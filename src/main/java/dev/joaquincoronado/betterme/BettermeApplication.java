package dev.joaquincoronado.betterme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BettermeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BettermeApplication.class, args);
    }

}
