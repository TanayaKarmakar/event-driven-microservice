package com.app.t2k.service.bootstrap;


import com.app.t2k.service.init.StreamInitializer;
import com.app.t2k.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TweetLoader implements CommandLineRunner {
    //private final TwitterToKafkaConfig twitterToKafkaConfig;
    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;

    @Override
    public void run(String... args) throws Exception {
        log.info("Application starts");
        //log.info(Arrays.toString(twitterToKafkaConfig.getTwitterKeywords().toArray(new String[]{})));
        streamInitializer.init();
        streamRunner.start();
    }
}
