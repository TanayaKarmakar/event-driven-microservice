package com.app.t2k.bootstrap;

import com.app.t2k.config.TwitterToKafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TweetLoader implements CommandLineRunner {
    private final TwitterToKafkaConfig twitterToKafkaConfig;

    @Override
    public void run(String... args) throws Exception {
        log.info("Application starts");
        log.info(Arrays.toString(twitterToKafkaConfig.getTwitterKeywords().toArray(new String[]{})));
    }
}
