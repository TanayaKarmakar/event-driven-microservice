package com.app.t2k.service.runner.impl;

import com.app.t2k.kafka.to.elastic.config.TwitterToKafkaConfig;
import com.app.t2k.service.exception.TwitterToKafkaException;
import com.app.t2k.service.listener.TwitterKafkaStatusListener;
import com.app.t2k.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author t0k02w6 on 20/06/21
 * @project event-driven-microservice
 */
@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "twitter-to-kafka.enable-mock-tweets", havingValue = "true")
public class MockKafkaStreamRunner implements StreamRunner {
    private final TwitterToKafkaConfig twitterToKafkaConfig;
    private final TwitterKafkaStatusListener listener;

    private static final Random RANDOM = new Random();

    private static final String[] words = {
            "Lorem",
            "ipsum",
            "dolor",
            "sit",
            "amet",
            "consectetuer",
            "adipiscing",
            "elit",
            "Maecenas",
            "porttitor",
            "conque",
            "massa",
            "Fusce",
            "posuere",
            "magna",
            "sed",
            "pulvinar",
            "ulticies",
            "purus",
            "lectus",
            "malesuada",
            "libero"
    };

    private static final String tweetAsRawJson = "{"
            + "\"created_at\":\"{0}\","
            + "\"id\": \"{1}\","
            + "\"text\": \"{2}\","
            + "\"user\": {\"id\": \"{3}\"}"
            + "}";

    private static final String TWITTER_STATUS_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    @Override
    public void start() throws TwitterException {
        String[] keyWords = twitterToKafkaConfig.getTwitterKeywords().toArray(new String[0]);
        int minTweetLength = twitterToKafkaConfig.getMockMinTweetLength();
        int maxTweetLength = twitterToKafkaConfig.getMockMaxTweetLength();
        long sleepTimeMs = twitterToKafkaConfig.getMockSleepMs();

        log.info("Starting mock filtering twitter streams for keywords: {}", Arrays.toString(keyWords));

       simulateTwitterStream(keyWords, minTweetLength, maxTweetLength, sleepTimeMs);
    }

    private void simulateTwitterStream(String[] keyWords, int minTweetLength, int maxTweetLength, long sleepTimeMs) throws TwitterException {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                while(true) {
                    String formattedTweetRawJson = getFormattedTweet(keyWords, minTweetLength, maxTweetLength);
                    Status status = TwitterObjectFactory.createStatus(formattedTweetRawJson);
                    listener.onStatus(status);
                    sleep(sleepTimeMs);
                }
            } catch(TwitterException exception) {
                log.error("Error creating twitter status", exception);
            }
        });
    }

    private void sleep(long sleepTimeMs) {
        try {
            Thread.sleep(sleepTimeMs);
        } catch (InterruptedException ex) {
            throw new TwitterToKafkaException("Error while sleeping for waiting new status to create!!");
        }
    }

    private String getFormattedTweet(String[] keyWords, int minTweetLength, int maxTweetLength) {
        String[] params = new String[] {
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern(TWITTER_STATUS_DATE_FORMAT, Locale.ENGLISH)),
                String.valueOf(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE)),
                getRandomTweetContent(keyWords, minTweetLength, maxTweetLength),
                String.valueOf(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE))
        };
        return formatTweetAsJsonWithParams(params);
    };

    private String formatTweetAsJsonWithParams(String[] params) {
        String tweet = tweetAsRawJson;
        for(int i = 0; i < params.length; i++) {
            tweet = tweet.replace("{" + i + "}", params[i]);
        }
        return tweet;
    }

    private String getRandomTweetContent(String[] keyWords, int minTweetLength, int maxTweetLength) {
        StringBuilder tweet = new StringBuilder();
        int tweetLength = RANDOM.nextInt(maxTweetLength - minTweetLength + 1) + minTweetLength;
        return constructRandomTweet(keyWords, tweet, tweetLength);
    }

    private String constructRandomTweet(String[] keyWords, StringBuilder tweet, int tweetLength) {
        for(int i = 0; i < tweetLength; i++) {
            tweet.append(words[RANDOM.nextInt(words.length)]).append(" ");
            if(i == tweetLength / 2) {
                tweet.append(keyWords[RANDOM.nextInt(keyWords.length)]).append(" ");
            }
        }
        return tweet.toString().trim();
    }
}
