package com.video.demo.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerComponent {

    @Scheduled(fixedDelay = 86400000)
    public void viewUpdate(){

    }
}
