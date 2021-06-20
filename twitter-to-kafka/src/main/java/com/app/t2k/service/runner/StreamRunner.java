package com.app.t2k.service.runner;

import twitter4j.TwitterException;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
public interface StreamRunner {
    void start() throws TwitterException;
}
