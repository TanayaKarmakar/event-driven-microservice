package com.app.t2k.service.runner.impl;


import com.app.t2k.kafka.to.elastic.config.TwitterToKafkaConfig;
import com.app.t2k.service.listener.TwitterKafkaStatusListener;
import com.app.t2k.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "twitter-to-kafka.enable-mock-tweets", havingValue = "false", matchIfMissing = true)
public class TwitterKafkaStreamRunner implements StreamRunner {
    private final TwitterToKafkaConfig twitterToKafkaConfig;
    private final TwitterKafkaStatusListener listener;
    private TwitterStream twitterStream;

    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        addFilter();
    }

    @PreDestroy
    public void shutdown() {
        if(!Objects.isNull(twitterStream)) {
            log.info("Closing twitter stream");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String[] keyWords = twitterToKafkaConfig.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keyWords);
        twitterStream.filter(filterQuery);

        log.info("Started filtering twitter stream for keywords: {}", Arrays.toString(keyWords));
    }
}
