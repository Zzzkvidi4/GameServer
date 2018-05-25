package com.zzzkvidi4.gameserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Роман on 24.05.2018.
 */

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String... args){
        SpringApplication.run(Application.class, args);
    }
}
