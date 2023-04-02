package com.duo.costsharingscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CostSharingSchedulerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(CostSharingSchedulerApplication.class, args);
    }
}
