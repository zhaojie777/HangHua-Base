package com.beetle.hanghua.account;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class ScheduledTask {

    @Scheduled(cron = "0 0 3  * * *")
    public void scheduled(){
        System.out.println("=====>>>>>使用cron{ " + System.currentTimeMillis());
    }
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        System.out.println("=====>>>>>使用fixedRate{ " + System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        System.out.println("=====>>>>>fixedDelay{ " + System.currentTimeMillis());
    }

}
