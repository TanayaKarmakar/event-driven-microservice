package com.app.t2k.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
@Slf4j
@Component
public class TwitterKafkaStatusListener extends StatusAdapter {
    @Override
    public void onStatus(Status status) {
        log.info("Twitter status with text: {}", status.getText());
    }
}
